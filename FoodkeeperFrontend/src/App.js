import React, { useState, useEffect } from "react";
import { Switch, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/css/bootstrap.css.map";

import "./App.css";
import "./Table.css"
import "./Create.css"
import "./media.css"

import AuthService from "./services/auth.service";
//Начальные страницы для пользователя
import Login from "./components/UserComponents/Login";
import Register from "./components/UserComponents/Register";
import Home from "./components/UserComponents/Home";
import Profile from "./components/UserComponents/Profile";

//Страницы ролей
import BoardUser from "./components/Boards/BoardUser";
import BoardModerator from "./components/Boards/Moderator/BoardModerator";
import BoardAdmin from "./components/Boards/BoardAdmin";

import FoodKeeperButtons from "./components/FoodKeeper/FoodKeeperBoard";
import ProductTable from "./components/FoodKeeper/ProductTable";



// import AuthVerify from "./common/AuthVerify";
import EventBus from "./common/EventBus";


const App = () => {
  const [showModeratorBoard, setShowModeratorBoard] = useState(false);
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
      setShowModeratorBoard(user.roles.includes("ROLE_MODERATOR"));
      setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
  }, []);

  const logOut = () => {
    AuthService.logout();
    setShowModeratorBoard(false);
    setShowAdminBoard(false);
    setCurrentUser(undefined);
  };

  return (
    <div className="container-xxl">
      <nav className="navbar navbar-expand-xl navbar-primary bg-darkblue">
        <Link to={"/"} className="navbar-brand">
          <img src={require('./logo.png')} width={125} height={75}></img>
        </Link>
        <div className="navbar-nav mr-auto">

          {currentUser && (
            <li className="nav-item">
              <Link to={"/foodkeeper"} className="nav-link">
                FoodKeeper
              </Link>
            </li>
          )}

          {showModeratorBoard && (
            <li className="nav-item">
              <Link to={"/mod"} className="nav-link">
                Панель модератора
              </Link>
            </li>
          )}

          {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/admin"} className="nav-link">
                Панель администратора
              </Link>
            </li>
          )}

          {/* {currentUser && (
            <li className="nav-item">
              <Link to={"/user"} className="nav-link">
                User
              </Link>
            </li>
          )} */}

          {/* {showModeratorBoard && (<li className="nav-item">
            <Link to={"/register"} className="nav-link">
              Регистрация
            </Link>
          </li>)} */}

        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                Профиль: {currentUser.username}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                Выход
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Вход
              </Link>
            </li>

            {showModeratorBoard && (<li className="nav-item">
              <Link to={"/signup"} className="nav-link">
                Регистрация
              </Link>
            </li>)}
          </div>
        )}
      </nav>

      <div className="">
        <Switch>
          <Route exact path={["/", "/home"]} component={Home} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/signup" component={Register} />
          <Route exact path="/profile" component={Profile} />

          
          <Route path="/user" component={BoardUser} />
          <Route path="/admin" component={BoardAdmin} />
          <Route exact path="/mod" component={BoardModerator} />

          <Route exact path="/foodkeeper" component={FoodKeeperButtons} />
          <Route path="/foodkeeper/products" component={ProductTable}/>
          {/* 
          <Route path="/mod/users-table" component={ModeratorUsersTable}/>
          <Route path="mod/roles-units-table" component={RolesUnitsTable}/>
          <Route exact path="/eventboard" component={BoardEvent} />
          <Route path="/eventboard/table" component={ButtonEventTable} />
          <Route path="/eventboard/create-event" component={ButtonCreateEvent} />
          <Route path="/eventboard/edit-event" component={ButtonEventEdit} />
          <Route path="/eventboard/event-logs" component={ButtonEventLogs} />
          <Route exact path="/eventboard/realize-event" component={ButtonEventRealization} />
          <Route exact path="/eventboard/trace-event" component={ButtonEventTrace} /> */}

          {/* <Route path="/eventboard/realize-event/st1/:id" component={RealizationState1} />
          <Route path="/eventboard/realize-event/st2/:id" component={RealizationState2} />
          <Route path="/eventboard/realize-event/st3/:id" component={RealizationState3} />

          <Route path="/eventboard/trace-event/st1/:id" component={CreatorState1} />
          <Route path="/eventboard/trace-event/st2/:id" component={CreatorState2} /> */}

        </Switch>
      </div>

      {/* <AuthVerify logOut={logOut}/> */}
    </div>
  );
};

export default App;
