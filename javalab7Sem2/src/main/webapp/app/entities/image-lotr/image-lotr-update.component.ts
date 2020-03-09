import { Component, OnInit, ElementRef } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IImageLotr, ImageLotr } from 'app/shared/model/image-lotr.model';
import { ImageLotrService } from './image-lotr.service';
import { IUnitLotr } from 'app/shared/model/unit-lotr.model';
import { UnitLotrService } from 'app/entities/unit-lotr/unit-lotr.service';

@Component({
  selector: 'jhi-image-lotr-update',
  templateUrl: './image-lotr-update.component.html'
})
export class ImageLotrUpdateComponent implements OnInit {
  isSaving: boolean;

  units: IUnitLotr[];

  editForm = this.fb.group({
    id: [],
    image: [],
    imageContentType: [],
    setUpDate: [],
    unitId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected imageService: ImageLotrService,
    protected unitService: UnitLotrService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ image }) => {
      this.updateForm(image);
    });
    this.unitService
      .query()
      .subscribe((res: HttpResponse<IUnitLotr[]>) => (this.units = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(image: IImageLotr) {
    this.editForm.patchValue({
      id: image.id,
      image: image.image,
      imageContentType: image.imageContentType,
      setUpDate: image.setUpDate != null ? image.setUpDate.format(DATE_TIME_FORMAT) : null,
      unitId: image.unitId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file: File = event.target.files[0];
        if (isImage && !file.type.startsWith('image/')) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      // eslint-disable-next-line no-console
      () => console.log('blob added'), // success
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const image = this.createFromForm();
    if (image.id !== undefined) {
      this.subscribeToSaveResponse(this.imageService.update(image));
    } else {
      this.subscribeToSaveResponse(this.imageService.create(image));
    }
  }

  private createFromForm(): IImageLotr {
    return {
      ...new ImageLotr(),
      id: this.editForm.get(['id']).value,
      imageContentType: this.editForm.get(['imageContentType']).value,
      image: this.editForm.get(['image']).value,
      setUpDate:
        this.editForm.get(['setUpDate']).value != null ? moment(this.editForm.get(['setUpDate']).value, DATE_TIME_FORMAT) : undefined,
      unitId: this.editForm.get(['unitId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IImageLotr>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUnitById(index: number, item: IUnitLotr) {
    return item.id;
  }
}
