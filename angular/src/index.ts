import "reflect-metadata";
import express from "express";
import routes from "./routes";
import i18next from 'i18next';
import i18nextMiddleware from 'i18next-http-middleware';
import Backend from 'i18next-fs-backend';
import { initializeDatabase } from "./database";

async function startServer() {
    try {
        await initializeDatabase();

        const app = express();

        await i18next
            .use(Backend)
            .use(i18nextMiddleware.LanguageDetector)
            .init({
                fallbackLng: 'en',
                backend: {
                    loadPath: './locales/{{lng}}/{{ns}}.json',
                },
            });

        app.use(i18nextMiddleware.handle(i18next));
        app.use(express.json());
        app.use("/api", routes);

        app.listen(3000, () => {
            console.log("Server started on port 3000");
        });
    } catch (error) {
        console.error("Error starting server:", error);
    }
}

startServer();
