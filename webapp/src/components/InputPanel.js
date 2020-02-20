import React from "react";
import { Component } from "react";
import Button from "@material-ui/core/Button";
import moment from "moment";

class InputPanel extends Component {
  constructor() {
    super();

    this.state = {
      value: ""
    };
  }

  handleChange = event => {
    this.setState({ value: event.target.value });
  };

  getDateTime = () => {
    const { calendar } = this.props;

    const date = calendar.date_time;

    const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
    const startTime = moment(date).format("dddd, MMMM Do YYYY, h:mm:ss a");
    const endTime = moment(date)
      .add(1, "hour")
      .format("h:mm:ss a");
    return `${startTime} ~ ${endTime} (${timezone})`;
  };

  resetTextFieldValue = () => {
    this.setState({ value: "" });
  };

  render() {
    const { calendar, onClickConfirm, isConfirmed } = this.props;
    const { value } = this.state;

    if (!calendar || calendar.length === 0) return <div></div>;

    if (isConfirmed)
      return (
        <div>
          <h3>
            <strong>COMPLTE - {this.getDateTime()}</strong>
          </h3>
        </div>
      );

    return (
      <div>
        <div style={{ marginBottom: "30px" }}>{this.getDateTime()}</div>
        <div style={{ margin: "5px" }}>REASON</div>
        <div>
          <textarea
            value={value}
            onChange={this.handleChange}
            rows="5"
            cols="50"
          ></textarea>
        </div>
        <div style={{ marginTop: "30px" }}>
          <Button
            variant="contained"
            color="primary"
            onClick={() =>
              onClickConfirm(calendar, value, this.resetTextFieldValue)
            }
          >
            Confirm Call
          </Button>
        </div>
      </div>
    );
  }
}

export default InputPanel;
