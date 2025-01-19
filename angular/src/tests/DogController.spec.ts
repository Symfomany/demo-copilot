import { Test, TestingModule } from '@nestjs/testing';
import  DogController from '../controller/DogController';
import  DogService  from '../service/DogService';
import { describe, expect, test } from '@jest/globals';

let controller: DogController;
let service: jest.Mocked<DogService>;


describe('DogController', () => {
    let controller: DogController;
    let service: DogService;

    beforeEach(async () => {
        const module: TestingModule = await Test.createTestingModule({
            controllers: [DogController],
            providers: [
                {
                    provide: DogService,
                    useValue: {
                        findAll: jest.fn().mockResolvedValue([{ id: 1, name: 'Rex' }]),
                        findOne: jest.fn().mockResolvedValue({ id: 1, name: 'Rex' }),
                        create: jest.fn().mockResolvedValue({ id: 1, name: 'Rex' }),
                        update: jest.fn().mockResolvedValue({ id: 1, name: 'Rex' }),
                        remove: jest.fn().mockResolvedValue({ id: 1, name: 'Rex' }),
                    },
                },
            ],
        }).compile();

        controller = module.get<DogController>(DogController);
        service = module.get<DogService>(DogService);
    });

    it('should be defined', () => {
        expect(controller).toBeDefined();
    });

    it('should return an array of dogs', async () => {
        expect(await controller.findAll()).toEqual([{ id: 1, name: 'Rex' }]);
    });


    it('should return a single dog', async () => {
        const result = await controller.findOne('1');
        expect(result).toEqual({ id: 1, name: 'Rex' });
        expect(service.findOne).toHaveBeenCalledWith(1);
    });

    it('should throw an exception for an invalid dog id', async () => {
        jest.spyOn(service, 'findOne').mockRejectedValueOnce(new Error('Dog not found'));
        await expect(controller.findOne('999')).rejects.toThrow('Dog not found');
    });


    it('should create a new dog', async () => {
        expect(await controller.createDog({ name: 'Rex' })).toEqual({ id: 1, name: 'Rex' });
    });

    it('should update a dog', async () => {
        expect(await controller.update('1', { name: 'Rex' })).toEqual({ id: 1, name: 'Rex' });
    });

    it('should delete a dog', async () => {
        expect(await controller.remove('1')).toEqual({ id: 1, name: 'Rex' });
    });
});