<template>
  <div id="left">
    <div id="time">
      <div>
        Current Time
      </div>
      <div>
        <table id="date">
          <tr>
            <td>Day</td>
            <td>Month</td>
            <td>Year</td>
            <td>Hour</td>
            <td>Min</td>
            <td>Sec</td>
          </tr>
          <tr>
            <td id="day">{{ day }}</td>
            <td id="month">{{ month }}</td>
            <td id="year">{{ year }}</td>
            <td id="hour">{{ hour }}</td>
            <td id="min">{{ minute }}</td>
            <td id="sec">{{ sec }}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

module.exports = {
  data: function () {
    return {
      day: '',
      month: '',
      year: '',
      hour: '',
      minute: '',
      sec: ''
    }
  },
  created () {
    let today = new Date()
    this.day = today.getDate()
    this.month = today.getMonth() + 1
    this.year = today.getFullYear()
    this.hour = today.getHours()
    this.minute = today.getMinutes()
    this.sec = today.getSeconds()

    setInterval(() => {

      today = new Date()

      this.day = today.getDate()
      this.month = today.getMonth() + 1
      this.year = today.getFullYear()
      this.hour = today.getHours()
      this.minute = today.getMinutes()
      this.sec = today.getSeconds()
    }, 1000)
  }
}
</script>

<style scoped>
#time div:first-child {
  background: #ad0aad;
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
  margin-left: 20px;
  margin-top: 20px;
  margin-right: 20px;
}

#time div:last-child {
  margin-left: 20px;
  margin-right: 20px;
}

#date {
  background: white;
  border-spacing: 20px 20px;
  width: 100%;
  border-radius: 0 0 8px 8px;
}

#date thead * {
  background: #671e67;
  font-size: 25px;
  border-radius: 8px 8px 8px 8px;
}

#date tr td {
  background: #985198;
  font-size: 20px;
  border-radius: 8px 8px 8px 8px;
}

#date tr:first-child td {
  background: #671e67;
}
</style>