import React, { useState, useEffect } from "react";
import { Switch, Route, Link } from "react-router-dom";

import EventBus from "../../common/EventBus";
import AuthService from "../../services/auth.service";

const FoodKeeperBoard = () => {
    const [content, setContent] = useState("");

    const [showModeratorBoard, setShowModeratorBoard] = useState(false);

    useEffect(() => {
        const user = AuthService.getCurrentUser();
    
        if (user) {
          setShowModeratorBoard(user.roles.includes("ROLE_MODERATOR"));
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
      };

      return (
        <div className="box-event">
            <div class="margin5">
                <h2><b>FoodKeeper</b></h2>
                <hr align="center" width="100%" size="2" color="red"/>
            </div>
            <div>
                <Link to={"foodkeeper/products"}><button type="button" class="btn btn-secondary">ProductTable</button></Link>
                <Link to={""}><button type="button" class="btn btn-secondary"></button></Link>
                <Link to={""}><button type="button" class="btn btn-secondary"></button></Link>
                <Link to={""}><button type="button" class="btn btn-secondary"></button></Link>
                <Link to={""}><button type="button" class="btn btn-secondary"></button></Link>
                <Link to={""}><button type="button" class="btn btn-secondary"></button></Link>
            </div>
        </div>
      )
    


}

export default FoodKeeperBoard;