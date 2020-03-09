import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILanguageLotr } from 'app/shared/model/language-lotr.model';
import { LanguageLotrService } from './language-lotr.service';

@Component({
  templateUrl: './language-lotr-delete-dialog.component.html'
})
export class LanguageLotrDeleteDialogComponent {
  language: ILanguageLotr;

  constructor(
    protected languageService: LanguageLotrService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.languageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'languageListModification',
        content: 'Deleted an language'
      });
      this.activeModal.dismiss(true);
    });
  }
}
