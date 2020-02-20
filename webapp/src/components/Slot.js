import React from "react";
import Button from "@material-ui/core/Button";
import moment from "moment";

const Slot = ({ ...props }) => {
  const { calendar, onClickCalendar } = props;

  const available = calendar.available;
  const date = moment(calendar.date_time).format("h:mm a");
  const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
  const displayDate = `${date} (${timezone})`;

  if (available) {
    return (
      <div>
        <Button
          variant="contained"
          color="primary"
          onClick={() => onClickCalendar(calendar)}
        >
          {displayDate}
        </Button>
      </div>
    );
  } else {
    return (
      <div>
        <Button
          variant="contained"
          // disabled
          onClick={() => alert("Not Available Time")}
        >
          {displayDate}
        </Button>
      </div>
    );
  }
};

export default Slot;
