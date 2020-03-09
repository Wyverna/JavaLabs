import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExpeditionLotr } from 'app/shared/model/expedition-lotr.model';
import { ExpeditionLotrService } from './expedition-lotr.service';

@Component({
  templateUrl: './expedition-lotr-delete-dialog.component.html'
})
export class ExpeditionLotrDeleteDialogComponent {
  expedition: IExpeditionLotr;

  constructor(
    protected expeditionService: ExpeditionLotrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.expeditionService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'expeditionListModification',
        content: 'Deleted an expedition'
      });
      this.activeModal.dismiss(true);
    });
  }
}
