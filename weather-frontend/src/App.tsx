import React from 'react';
import './App.css';
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Paper from '@material-ui/core/Paper';

interface City {
  id: number
  name: string
  country: string;
}

interface Temperature {
  date: string
  max: number
  min: number
  description: string
  suggestion: string
}

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    textControl: {
      margin: theme.spacing(8),
      minWidth: 360,
    },
    table: {
      minWidth: 450,
    },
  }),
);

function App() {

  const classes = useStyles();
  const [cities, setCities] = React.useState<City[]>([]);
  const [temperature, setTemperature] = React.useState<Temperature[]>([]);
  const [cityId, setCityId] = React.useState('');

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCityId(event.target.value);

    fetch('http://localhost:8080/weather/' + event.target.value)
      .then(response => response.json())
      .then(data => setTemperature(data))
      .catch((error) => {
        console.error('Error:', error);
      });
  };


  React.useEffect(() => {

    fetch('http://localhost:8080/cities')
      .then(response => response.json())
      .then(data => setCities(data))
      .catch((error) => {
        console.error('Error:', error);
      });
  }, []);

  return (
    <div className="App">
      <TextField
          id="city"
          select
          label="Select City"
          value={cityId}
          onChange={handleChange}
          helperText="Please select city"
          variant="outlined"
          className={classes.textControl}
        >
          {cities.map(city => (
            <MenuItem key={city.id} value={city.id}>
              {city.name}, {city.country}
            </MenuItem>
          ))}
        </TextField>
      <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Date</TableCell>
              <TableCell>Max&nbsp;(Celcius)</TableCell>
              <TableCell>Min&nbsp;(Celcius)</TableCell>
              <TableCell>Weather</TableCell>
              <TableCell>Suggestion</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {temperature.map((row) => (
              <TableRow key={row.date}>
                <TableCell component="th" scope="row">
                  {row.date}
                </TableCell>
                <TableCell>{row.max}</TableCell>
                <TableCell>{row.min}</TableCell>
                <TableCell>{row.description}</TableCell>
                <TableCell>{row.suggestion}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
}

export default App;
