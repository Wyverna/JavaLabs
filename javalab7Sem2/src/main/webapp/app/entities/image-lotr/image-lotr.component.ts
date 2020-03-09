import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IImageLotr } from 'app/shared/model/image-lotr.model';
import { ImageLotrService } from './image-lotr.service';
import { ImageLotrDeleteDialogComponent } from './image-lotr-delete-dialog.component';

@Component({
  selector: 'jhi-image-lotr',
  templateUrl: './image-lotr.component.html'
})
export class ImageLotrComponent implements OnInit, OnDestroy {
  images: IImageLotr[];
  eventSubscriber: Subscription;

  constructor(
    protected imageService: ImageLotrService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.imageService.query().subscribe((res: HttpResponse<IImageLotr[]>) => {
      this.images = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInImages();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IImageLotr) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInImages() {
    this.eventSubscriber = this.eventManager.subscribe('imageListModification', () => this.loadAll());
  }

  delete(image: IImageLotr) {
    const modalRef = this.modalService.open(ImageLotrDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.image = image;
  }
}
