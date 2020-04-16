# covid19-tracker-back
## This API will provided online information about where coronavirus is currently active, how many cases in the world, etc

This API shoud provide realtime information about the Covid19 situation. This API was built on top of Coronavirus monitor API by astsiatsko (Ref URL: https://rapidapi.com/astsiatsko/api-monitor/details)

We pull data from https:/-monitor.p.rapidapi.com/* API endpoints and modify the output as our API designs. We pick up the most relevant resources from the API. Some endpoints gets information every minute, some other one time per 10 minutes. 
## URL
The Rest API service app can be found at https://covid19-monitor-api.herokuapp.com/

## Usage
prefix: "user/api/v1/coronavirus"

| Routes                           | Description                                                                |HTTP Methods|
|----------------------------------|----------------------------------------------------------------------------|------------|
| /masks               | Use these instructions as pictures to avoid infection                      | GET        |
| /affected_countries  | List of affected countries by date                                         | GET        |
| /cases_by_particular_country/<string:country>'| Return history by particular country that was provided in parameters | GET        |
| /history_by_country_and_date/<string:country>/<string:date>| Get history by country and date      | GET        |
| /cases_by_country    | Return JSON with cases mapped by country map                               | GET        |
| /canada_stat_small   | This endpoints provides really brief information about situation in Canada | GET        |
| /canada              | This endpoint returns data about cases or coronavirus in Canada by state   | GET        |
| /latest_stat_by_country/<string:country> | Provides the most recent stat by provided country name | GET        |
| /worldstats           | Total statistic for world cases, deaths, etc                              | GET        |