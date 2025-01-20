import { ComponentFixture, TestBed } from '@angular/core/testing';
import DogController from './DogController';
import DogService from '../service/DogService';

describe('DogController', () => {
  let controller: DogController;
  let dogServiceSpy: jasmine.SpyObj<DogService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('DogService', ['findAll', 'findOne', 'create', 'update', 'remove']);

    await TestBed.configureTestingModule({
      declarations: [ DogController ],
      providers: [
        { provide: DogService, useValue: spy }
      ]
    }).compileComponents();

    dogServiceSpy = TestBed.inject(DogService) as jasmine.SpyObj<DogService>;
  });

  beforeEach(() => {
    controller = TestBed.inject(DogController);
  });

  it('should create', () => {
    expect(controller).toBeTruthy();
  });

  it('should return an array of dogs', async () => {
    const expectedDogs = [{ id: 1, name: 'Rex', breed: 'German Shepherd', age: 5 }];
    dogServiceSpy.findAll.and.resolveTo(expectedDogs);

    const result = await controller.findAll();
    expect(result).toEqual(expectedDogs);
    expect(dogServiceSpy.findAll).toHaveBeenCalled();
  });

  it('should return a single dog', async () => {
    const expectedDog = { id: 1, name: 'Rex', breed: 'German Shepherd', age: 5 };
    dogServiceSpy.findOne.and.resolveTo(expectedDog);

    const result = await controller.findOne('1');
    expect(result).toEqual(expectedDog);
    expect(dogServiceSpy.findOne).toHaveBeenCalledWith(1);
  });

  it('should create a new dog', async () => {
    const newDog = { name: 'Buddy', breed: 'Golden Retriever', age: 3 };
    const createdDog = { id: 2, ...newDog };
    dogServiceSpy.create.and.resolveTo(createdDog);

    const result = await controller.create(newDog);
    expect(result).toEqual(createdDog);
    expect(dogServiceSpy.create).toHaveBeenCalledWith(newDog);
  });

  it('should update a dog', async () => {
    const updatedDog = { id: 1, name: 'Rex', breed: 'German Shepherd', age: 6 };
    dogServiceSpy.update.and.resolveTo(updatedDog);

    const result = await controller.update('1', { age: 6 });
    expect(result).toEqual(updatedDog);
    expect(dogServiceSpy.update).toHaveBeenCalledWith(1, { age: 6 });
  });

  it('should remove a dog', async () => {
    dogServiceSpy.remove.and.resolveTo(undefined);

    await controller.remove('1');
    expect(dogServiceSpy.remove).toHaveBeenCalledWith(1);
  });
});
