import { Apartment } from "@mui/icons-material";
import axios from "axios";

const API_URL = "http://localhost:8080/api/products";

const getAllProducts = () => {
    return axios.get(API_URL + "/all-products");
};

const addProduct = (name, foodType, energy, fat, protein, carbs, weight) => {
    return axios.post(API_URL + "/product", {
        name,
        foodType,
        energy,
        fat, 
        protein,
        carbs,
        weight
    }) 
}

const getFoodTypes = () => {
    return axios.get(API_URL + "/get-foodtypes");
}

export default {
    getAllProducts,
    addProduct,
    getFoodTypes,
};