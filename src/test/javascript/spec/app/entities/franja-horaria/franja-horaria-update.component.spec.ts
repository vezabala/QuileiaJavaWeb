import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { QuileiaJavaWebTestModule } from '../../../test.module';
import { FranjaHorariaUpdateComponent } from 'app/entities/franja-horaria/franja-horaria-update.component';
import { FranjaHorariaService } from 'app/entities/franja-horaria/franja-horaria.service';
import { FranjaHoraria } from 'app/shared/model/franja-horaria.model';

describe('Component Tests', () => {
  describe('FranjaHoraria Management Update Component', () => {
    let comp: FranjaHorariaUpdateComponent;
    let fixture: ComponentFixture<FranjaHorariaUpdateComponent>;
    let service: FranjaHorariaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [QuileiaJavaWebTestModule],
        declarations: [FranjaHorariaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FranjaHorariaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FranjaHorariaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FranjaHorariaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FranjaHoraria(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FranjaHoraria();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
