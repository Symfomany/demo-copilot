import { Router } from 'express';
import DogController from './controller/DogController';


const router = Router();
const dogController = new DogController();

router.get("/dogs", dogController.getDogs.bind(dogController));
router.get("/dogs/:id", dogController.getDogById.bind(dogController));
router.post("/dogs", dogController.createDog.bind(dogController));
router.put("/dogs/:id", dogController.updateDog.bind(dogController));
router.delete("/dogs/:id", dogController.deleteDog.bind(dogController));

export default router;