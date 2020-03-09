import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImageLotr } from 'app/shared/model/image-lotr.model';
import { ImageLotrService } from './image-lotr.service';

@Component({
  templateUrl: './image-lotr-delete-dialog.component.html'
})
export class ImageLotrDeleteDialogComponent {
  image: IImageLotr;

  constructor(protected imageService: ImageLotrService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.imageService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'imageListModification',
        content: 'Deleted an image'
      });
      this.activeModal.dismiss(true);
    });
  }
}
