import { DataSource } from "typeorm";
import Dog from "./entity/Dog";

let dataSource: DataSource;

export async function initializeDatabase() {
    if (!dataSource) {
        dataSource = new DataSource({
            type: "sqlite",
            database: "dogs.sqlite",
            entities: [Dog],
            synchronize: true,
            logging: false
        });
        await dataSource.initialize();
    }
    return dataSource;
}

export function getDataSource() {
    if (!dataSource || !dataSource.isInitialized) {
        throw new Error("Database connection not initialized");
    }
    return dataSource;
}
