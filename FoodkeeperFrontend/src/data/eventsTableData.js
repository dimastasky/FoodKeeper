import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import EventBus from "../../common/EventBus";

import AuthService from "../../services/auth.service";
import EventBoardService from "../../services/events.service";



export default function DataTable() {

    const [eventsData, setTableData] = useState([]);
  
    useEffect(() => {
      getAllEvents()
    }, [])
  
    const getAllEvents = () => {
      EventBoardService.getAllEvents().then((response) => {
        setTableData(response.data)
        console.log(response.data);
      }).catch(error => {
        console.log(error);
      })
    }
  
    const [showEventTable, setShowEventTable] = useState(false);
  
    useEffect(() => {
      const user = AuthService.getCurrentUser();
  
      if (user) {
        setShowEventTable(user.roles.includes("ROLE_USER"))
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
      setShowEventTable(false);
    };
  
    return (
      <div style={{ height: 800, width: '100%' }}>
        { showEventTable && (<DataGrid
          rows={rows}
          columns={columns}
        //pageSize={8}
        // rowsPerPageOptions={[8]}
        // checkboxSelection
        />)
        }
      </div>
    );
  }