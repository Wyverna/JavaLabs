import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Javalab7Sem2TestModule } from '../../../test.module';
import { UnitLotrDetailComponent } from 'app/entities/unit-lotr/unit-lotr-detail.component';
import { UnitLotr } from 'app/shared/model/unit-lotr.model';

describe('Component Tests', () => {
  describe('UnitLotr Management Detail Component', () => {
    let comp: UnitLotrDetailComponent;
    let fixture: ComponentFixture<UnitLotrDetailComponent>;
    const route = ({ data: of({ unit: new UnitLotr(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Javalab7Sem2TestModule],
        declarations: [UnitLotrDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UnitLotrDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UnitLotrDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.unit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
