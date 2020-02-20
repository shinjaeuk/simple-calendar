import React from "react";
import Slot from "./Slot";
import moment from "moment";

const SlotPanel = ({ ...props }) => {
  const { date, calendars, onClickCalendar, mentor } = props;

  const filteredCalendars = calendars
    ? calendars.filter(c => {
        const selectedDate = moment(date);
        const calendarDate = moment(c.date_time);

        return selectedDate.isSame(calendarDate, "day");
      })
    : [];

  if (!mentor) return <div></div>;

  if (filteredCalendars.length === 0)
    return (
      <div>
        <h1>{mentor.name}</h1>
        <h3>No Slot</h3>
      </div>
    );

  return (
    <div>
      <div>
        <h1>{mentor.name}</h1>
      </div>
      <div>
        {filteredCalendars.map((c, i) => {
          return (
            <div key={i} style={{ padding: "10px" }}>
              <Slot calendar={c} onClickCalendar={onClickCalendar}></Slot>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default SlotPanel;
