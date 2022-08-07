
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
    // .then(() => {
    //     console.log('Product Added');
    //     this.props.getAllProducts();
    // })
    // .catch(err => console.log(err))
}

const getFoodTypes = () => {
    return axios.get(API_URL + "/get-foodtypes");
}

const deleteProduct = (id) => {
    return axios.delete(API_URL + "/product/" + id);
}

const getProduct = (id) => {
    return axios.get(API_URL + "/product/" + id);
}

export default {
    getAllProducts,
    addProduct,
    getFoodTypes,
    deleteProduct,
    getProduct
};