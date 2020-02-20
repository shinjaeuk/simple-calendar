import React from "react";
import { Component } from "react";
import "./App.css";
import Grid from "@material-ui/core/Grid";
import Flatpickr from "react-flatpickr";

import "flatpickr/dist/themes/material_green.css";
import axios from "axios";
import SlotPanel from "./components/SlotPanel";
import Box from "@material-ui/core/Box";
import InputPanel from "./components/InputPanel";

class App extends Component {
  constructor() {
    super();

    this.state = {
      date: new Date(),
      mentor: null,
      calendars: null,
      calendar: null,
      isConfirmed: false
    };
  }

  componentDidMount = async () => {
    const agenda = await axios.get(`/mentors/1/agenda`);
    const mentor = agenda.data.mentor;
    const calendars = agenda.data.calendars;

    this.setState({
      calendars,
      mentor
    });
  };

  onClickCalendar = calendar => {
    this.setState({
      showInput: true,
      calendar: calendar,
      isConfirmed: false
    });
  };

  onClickConfirm = async (calendar, reason, resetTextFieldValue) => {
    const { mentor, calendars } = this.state;
    await axios.post(`/mentors/${mentor.id}/agenda/${calendar.id}`, {
      reasone: reason
    });

    calendars.filter(c => c.id === calendar.id)[0].available = false;

    resetTextFieldValue();

    this.setState({ calendars, isConfirmed: true });
  };

  onChange = date => {
    this.setState({
      date: date[0],
      calendar: null,
      isConfirmed: false
    });
  };

  render() {
    const { date, mentor, isConfirmed, calendars, calendar } = this.state;

    return (
      <div className="App">
        <Grid container spacing={3}>
          <Grid item xs={12} sm={12}>
            <Box bgcolor="lightgray" color="black" p={2}>
              SELECT =>
              <Flatpickr
                value={date}
                onChange={date => {
                  this.onChange(date);
                }}
              />
            </Box>
          </Grid>
          <Grid item xs={12} sm={12}>
            <Box bgcolor="lightgray" color="black" p={6}>
              <SlotPanel
                mentor={mentor}
                calendars={calendars}
                onClickCalendar={this.onClickCalendar}
                date={date}
              ></SlotPanel>
            </Box>
          </Grid>
          <Grid item xs={12} sm={12}>
            <Box bgcolor="lightgray" color="black" p={6}>
              <InputPanel
                onClickConfirm={this.onClickConfirm}
                calendar={calendar}
                isConfirmed={isConfirmed}
              ></InputPanel>
            </Box>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default App;
