import { useScrollTrigger } from "@mui/material";
import React, { useState, useRef, useEffect } from "react";
import CheckButton from "react-validation/build/button";
import Form from "react-validation/build/form";

import TextField from '@mui/material/TextField';
import Select from '@mui/material/Select';
import LoadingButton from '@mui/lab/LoadingButton';
import MenuItem from '@mui/material/MenuItem';


import ProductsService from "../../services/products.service";
import ProductTable from "./ProductTable";

const AddProduct = () => {
    const [name, setName] = useState("");
    const [foodType, setFoodType] = useState("");
    const [energy, setEnergy] = useState("");
    const [fats, setFat] = useState("");
    const [protein, setProtein] = useState("");
    const [carbs, setCarbs] = useState("");
    const [weight, setWeight] = useState("");

    const onChangeName = (e) => {
        setName(e.target.value);
    }

    const onChangeFoodType = (e) => {
        setFoodType(e.target.value);
    }

    const onChangeEnergy = (e) => {
        setEnergy(e.target.value);
    }

    const onChangeFats = (e) => {
        setFat(e.target.value);
    }

    const onChangeProtein = (e) => {
        setProtein(e.target.value);
    }

    const onChangeCarbs = (e) => {
        setCarbs(e.target.value);
    }

    const onChangeWeight = (e) => {
        setWeight(e.target.value);
    }

    // TODO: Добавить список food type

    const [foodTypeData, setFoodTypeData] = useState([]);
    const getFoodTypeData = async () => {
        const res = await ProductsService.getFoodTypes();
        //console.log(data);
        const data = res.data

        const foodtypeDataArray = data.map(obj => ({
            "value": obj.id,
            "key": obj.name
        }))

        setFoodTypeData(foodtypeDataArray)
    }


    useEffect(() => {
        getFoodTypeData();
    }, [])

    const [successful, setSuccessful] = useState(false);
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);
    const checkBtn = useRef();
    const form = useRef();

    const handleCreate = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);
        setSuccessful(false);

        form.current.validateAll();

        if (checkBtn.current.context._errors.length === 0) {
            ProductsService.addProduct(name, foodType, energy, fats, protein, carbs, weight).then(
                (response) => {
                    setMessage(response.data.message);
                    setSuccessful(true);
                    setLoading(false);
                    setName("");
                    setFoodType("");
                    setEnergy("");
                    setFat("");
                    setProtein("");
                    setCarbs("");
                    setWeight("");
                    ProductTable.getAllProducts();

                },
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setLoading(false);
                    setMessage(resMessage);
                    setSuccessful(false);
                }
            );
        }
        else {
            setLoading(false);
        }
    };

    

    return (
        <div>
            <Form onSubmit={handleCreate} ref={form}>

                <tr>
                    <td>
                        <TextField
                            label="Наименование"
                            placeholder=""
                            value={name}
                            onChange={onChangeName}
                            type="text"
                        />
                    </td>
                    <td>
                        <Select
                            label="Категория"
                            placeholder=""
                            options={foodTypeData}
                            value={foodTypeData.find(obj => obj.value === foodType)}
                            onChange={onChangeFoodType}

                        >
                            {foodTypeData.map((obj) => (
                                <MenuItem
                                    value={obj.value}
                                    selected={foodTypeData === obj.value}
                                >
                                    {obj.key}
                                </MenuItem>
                            ))}
                        </Select>
                    </td>
                    <td>
                        <TextField
                            label="Калории"
                            placeholder=""
                            value={energy}
                            onChange={onChangeEnergy}
                            type="text"
                        />
                    </td>
                    <td>
                        <TextField
                            label="Углеводы"
                            placeholder=""
                            value={carbs}
                            onChange={onChangeCarbs}
                            type="text"
                        />
                    </td>
                    <td>
                        <TextField
                            label="Белки"
                            placeholder=""
                            value={protein}
                            onChange={onChangeProtein}
                            type="text"
                        />
                    </td>
                    <td>
                        <TextField
                            label="Жиры"
                            placeholder=""
                            value={fats}
                            onChange={onChangeFats}
                            type="text"
                        />
                    </td>
                    <td>
                        <TextField
                            label="Вес"
                            placeholder=""
                            value={weight}
                            onChange={onChangeWeight}
                            type="text"
                        />
                    </td>

                    <td>
                        <LoadingButton
                            variant="contained"
                            onClick={handleCreate}
                            loading={loading}
                            sx={{ my: 5 }}
                        >
                            Добавить
                        </LoadingButton>
                    </td>
                </tr>

                {message && (
                    <div className="form-group">
                        <div
                            className={
                                successful ? "alert alert-success" : "alert alert-danger"
                            }
                            role="alert"
                        >
                            {message}
                        </div>
                    </div>
                )}
                <CheckButton style={{ display: "none" }} ref={checkBtn} />
            </Form>
        </div>
    );

}

export default AddProduct;