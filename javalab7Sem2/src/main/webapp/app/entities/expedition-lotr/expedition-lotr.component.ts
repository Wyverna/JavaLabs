import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IExpeditionLotr } from 'app/shared/model/expedition-lotr.model';
import { ExpeditionLotrService } from './expedition-lotr.service';
import { ExpeditionLotrDeleteDialogComponent } from './expedition-lotr-delete-dialog.component';

@Component({
  selector: 'jhi-expedition-lotr',
  templateUrl: './expedition-lotr.component.html'
})
export class ExpeditionLotrComponent implements OnInit, OnDestroy {
  expeditions: IExpeditionLotr[];
  eventSubscriber: Subscription;

  constructor(
    protected expeditionService: ExpeditionLotrService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll() {
    this.expeditionService.query().subscribe((res: HttpResponse<IExpeditionLotr[]>) => {
      this.expeditions = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInExpeditions();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IExpeditionLotr) {
    return item.id;
  }

  registerChangeInExpeditions() {
    this.eventSubscriber = this.eventManager.subscribe('expeditionListModification', () => this.loadAll());
  }

  delete(expedition: IExpeditionLotr) {
    const modalRef = this.modalService.open(ExpeditionLotrDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.expedition = expedition;
  }
}
