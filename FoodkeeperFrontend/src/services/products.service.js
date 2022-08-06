import axios from "axios";

const API_URL = "http://localhost:8080/api/products";

const getAllProducts = () => {
    return axios.get(API_URL + "/all-products");
};

const addProduct = (name, foodtype, energy, fat, protein, carbs, weight) => {
    return axios.post(API_URL + "/product", {
        name,
        foodtype,
        energy,
        fat, 
        protein,
        carbs,
        weight
    }) 
}

export default {
    getAllProducts,
    addProduct,
};