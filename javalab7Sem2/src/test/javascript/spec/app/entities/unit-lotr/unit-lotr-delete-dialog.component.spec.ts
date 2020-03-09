import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Javalab7Sem2TestModule } from '../../../test.module';
import { UnitLotrDeleteDialogComponent } from 'app/entities/unit-lotr/unit-lotr-delete-dialog.component';
import { UnitLotrService } from 'app/entities/unit-lotr/unit-lotr.service';

describe('Component Tests', () => {
  describe('UnitLotr Management Delete Component', () => {
    let comp: UnitLotrDeleteDialogComponent;
    let fixture: ComponentFixture<UnitLotrDeleteDialogComponent>;
    let service: UnitLotrService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Javalab7Sem2TestModule],
        declarations: [UnitLotrDeleteDialogComponent]
      })
        .overrideTemplate(UnitLotrDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UnitLotrDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UnitLotrService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
