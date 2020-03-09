import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Javalab7Sem2TestModule } from '../../../test.module';
import { LanguageLotrComponent } from 'app/entities/language-lotr/language-lotr.component';
import { LanguageLotrService } from 'app/entities/language-lotr/language-lotr.service';
import { LanguageLotr } from 'app/shared/model/language-lotr.model';

describe('Component Tests', () => {
  describe('LanguageLotr Management Component', () => {
    let comp: LanguageLotrComponent;
    let fixture: ComponentFixture<LanguageLotrComponent>;
    let service: LanguageLotrService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Javalab7Sem2TestModule],
        declarations: [LanguageLotrComponent],
        providers: []
      })
        .overrideTemplate(LanguageLotrComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LanguageLotrComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LanguageLotrService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LanguageLotr(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.languages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
