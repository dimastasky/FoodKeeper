import axios from "axios";

const API_URL = "http://localhost:8080/api/warehouse";

const getAllWarehouses = () => {
    return axios.get(API_URL + "/all-warehouses");
}

const getAllUserWarehouses = (user) => {
    return axios.post(API_URL + "/all-user-warehouses", {
        user
    });
}

const getWarehouse = (id) => {
    return axios.get(API_URL + "/warehouse/" + id);
}

const createWarehouse = (id) => {
    return axios.post(API_URL + "/warehouse");
}


const deleteWarehouse = (id) => {
    return axios.delete(API_URL + "/warehouse/" + id);
}

const addProductToWarehouse = (warehouse_id, product_id, count, weight, bestBefore) => {
    return axios.post(API_URL + "/warehouse/" + warehouse_id + "/record", {
        product_id,
        count,
        weight,
        bestBefore
    })
}




export default {
    getAllWarehouses,
    getAllUserWarehouses,
    getWarehouse,
    createWarehouse,
    deleteWarehouse,
    addProductToWarehouse
};