import { Request, Response } from 'express';
import DogService  from '../service/DogService';
import Dog from '../entity/Dog';

export default class DogController {
    private dogService: DogService;

    constructor() {
        this.dogService = new DogService();
    }
    public async createDog(req: Request, res: Response): Promise<void> {
        try {
            const dog: Dog = req.body;
            const newDog = await this.dogService.create(dog);
            res.status(201).json(newDog);
        } catch (error) {
            res.status(500).json({ message: req.t('error.message', { error: (error as Error).message }) });
        }
    }

    public async getDogs(req: Request, res: Response): Promise<void> {
        try {
            const dogs = await this.dogService.findAll();
            res.status(200).json(dogs);
        } catch (error) {
            res.status(500).json({ message: req.t('error.message', { error: (error as Error).message }) });
        }
    }

    public async getDogById(req: Request, res: Response): Promise<void> {
        try {
            const dogId = parseInt(req.params.id);
            const dog = await this.dogService.findOne(dogId);
            if (dog) {
                res.status(200).json(dog);
            } else {
                res.status(404).json({ message: req.t('dog.notFound') });
            }
        } catch (error) {
            res.status(500).json({ message: req.t('error.message', { error: (error as Error).message }) });
        }
    }
    public async updateDog(req: Request, res: Response): Promise<void> {
        try {
            const dogId: number = parseInt(req.params.id, 10);
            const dogData: Dog = req.body;
            const updatedDog = await this.dogService.update(dogId, dogData);
            if (updatedDog) {
            res.status(200).json(updatedDog);
            } else {
            res.status(404).json({ message: req.t('dog.notFound') });
            }
        } catch (error) {
            res.status(500).json({ message: req.t('error.message', { error: (error as Error).message }) });
        }
    }

    public async deleteDog(req: Request, res: Response): Promise<void> {
        try {
            const dogId = parseInt(req.params.id);
            const deleted = await this.dogService.delete(dogId);
            if (deleted) {
                res.status(200).json({ message: req.t('dog.deletedSuccessfully') });
            } else {
                res.status(404).json({ message: req.t('dog.notFound') });
            }
        } catch (error) {
            res.status(500).json({ message: req.t('error.message', { error: (error as Error).message }) });
        }
    }
}