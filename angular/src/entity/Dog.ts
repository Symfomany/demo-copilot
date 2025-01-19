import { Entity, PrimaryGeneratedColumn, Column } from "typeorm";

@Entity()
export default class Dog {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column()
    name!: string;

    @Column()
    breed!: string;

    @Column()
    age!: number;
}
