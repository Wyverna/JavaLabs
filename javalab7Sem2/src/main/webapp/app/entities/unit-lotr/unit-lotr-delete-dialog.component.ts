import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUnitLotr } from 'app/shared/model/unit-lotr.model';
import { UnitLotrService } from './unit-lotr.service';

@Component({
  templateUrl: './unit-lotr-delete-dialog.component.html'
})
export class UnitLotrDeleteDialogComponent {
  unit: IUnitLotr;

  constructor(protected unitService: UnitLotrService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.unitService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'unitListModification',
        content: 'Deleted an unit'
      });
      this.activeModal.dismiss(true);
    });
  }
}
