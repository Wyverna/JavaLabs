import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUnitLotr } from 'app/shared/model/unit-lotr.model';
import { UnitLotrService } from './unit-lotr.service';
import { UnitLotrDeleteDialogComponent } from './unit-lotr-delete-dialog.component';

@Component({
  selector: 'jhi-unit-lotr',
  templateUrl: './unit-lotr.component.html'
})
export class UnitLotrComponent implements OnInit, OnDestroy {
  units: IUnitLotr[];
  eventSubscriber: Subscription;

  constructor(
    protected unitService: UnitLotrService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.unitService.query().subscribe((res: HttpResponse<IUnitLotr[]>) => {
      this.units = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInUnits();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUnitLotr) {
    return item.id;
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  registerChangeInUnits() {
    this.eventSubscriber = this.eventManager.subscribe('unitListModification', () => this.loadAll());
  }

  delete(unit: IUnitLotr) {
    const modalRef = this.modalService.open(UnitLotrDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.unit = unit;
  }
}
