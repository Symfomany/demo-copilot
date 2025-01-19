import { getDataSource } from '../database';
import Dog from '../entity/Dog';
import { Repository } from 'typeorm';

export default class DogService {
    private getDogRepository(): Repository<Dog> {
        return getDataSource().getRepository(Dog);
    }

    async findAll(): Promise<Dog[]> {
        return await this.getDogRepository().find();
    }

    async findOne(id: number): Promise<Dog | null> {
        return await this.getDogRepository().findOneBy({ id });
    }

    async create(dog: Dog): Promise<Dog> {
        return await this.getDogRepository().save(dog);
    }

    async update(id: number, updatedDog: Partial<Dog>): Promise<Dog | null> {
        await this.getDogRepository().update(id, updatedDog);
        return this.findOne(id);
    }

    async delete(id: number): Promise<boolean> {
        const result = await this.getDogRepository().delete(id);
        return result.affected !== 0;
    }
}
