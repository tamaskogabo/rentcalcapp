<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/tamaskogabo/rentcalcapp">
    <img src="images/clocks.jpg" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Rent Calculator App</h3>

  <p align="center">
    I made this project, because I was tired of calculating my rent, and bills for my
apartment every month. It is a full stack calculator app, with React and MUI frontend,
and Spring Boot backend with PostgreSQL DB connection, using JDBC, and a single user configured Spring Security layer.
    <br />
    <a href="https://github.com/tamaskogabo/rentcalcapp"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/tamaskogabo/rentcalcapp">View Demo</a>
    ·
    <a href="https://github.com/tamaskogabo/rentcalcapp/issues">Report Bug</a>
    ·
    <a href="https://github.com/tamaskogabo/rentcalcapp/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

-   [![React][React.js]][React-url]
-   [![Spring-Security][Spring-Security]][Spring-Security-url]
-   [![Javascript][Javascript]][Javascript-url]
-   [![Java][Java]][Java-url]
-   [![Spring][Spring]][Spring-url]
-   [![PostgreSQL][PostgreSQL]][PostgreSQL-url]
-   [![MUI][MUI]][MUI-url]


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

* npm
  ```sh
  npm install npm@latest -g
  ```
* Install JDK:
https://www.oracle.com/java/technologies/downloads/
* Install Maven:
https://maven.apache.org/install.html
* Install Docker Desktop:
https://www.docker.com/products/docker-desktop/

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/tamaskogabo/rentcalcapp.git
   ```
2. Install NPM packages in the frontend folder
   ```sh
   ./frontend/npm install
   ```
3. Create a React build in the frontend folder
```sh
   ./frontend/npm run build
```
4. Copy the newly created build folder's contents from the frontend directory to the backend/src/main/resources/static folder. (Static folder might have to be created in resources!)

5. Compile a .jar file in the backend folder, using Maven
```sh
   ./backend/mvn package
```
6. [OPTIONAL] Modify the USERNAME and PASSWORD environment variables in the docker-compose.yml file.

7. Run docker compose in the backend folder
```
   ./backend/docker compose up
```
8. Check out the application on your port, http://localhost:8080. The default username and password are admin, admin.

!!! For the calculations to appear, there has to be two entries in the DB, one from last month, and one from the current month. From the frontend, it is only possible to send data once a month, to avoid duplications of entries, so to see the calculations, you have to post not just one DB entry from the frontend (which handles the current timestamp automatically), but you also have to add one entry for the previous month as well in the DB. This is easily done, if you open the terminal of the DB in docker, and insert a line with the previous months date into the table !!!

Type the following commands into the terminal of the DB in docker desktop:
```sh
psql -U postgres

INSERT INTO clockstands VALUES (default, TO_DATE('YOUR CURRENT DATE WITH THE PREVIOUS MONTH LIKE THIS: 20/06/2023', 'DD/MM/YYYY'), 500, 500, 5000, 5000, 5635);
```


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

The application can be used to calculate the base rent, water usages, electricity costs of my rented apartment, based on how I am personally supposed to wire it each month. The costs to send depend on the current month, different calculations are made every odd month, every quarter-year month, and on every regular month.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Create Basic Frontend view and Backend API endpoints with DB connection
- [ ] Add better calculation formulas on the frontend, based on current date
- [ ] Create Post page form to send data to the DB and a table page to display all the stored clockstands
- [ ] Dockerize the application to make it easier to install on any computer

See the [open issues](https://github.com/tamaskogabo/rentcalcapp/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Gábor Tamaskó - tamaskogabo@gmail.com

Project Link: [https://github.com/tamaskogabo/rentcalcapp](https://github.com/tamaskogabo/rentcalcapp)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [] Thanks to Codecool for the amazing help with learning how to code.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/tamaskogabo/rentcalcapp.svg?style=for-the-badge
[contributors-url]: https://github.com/tamaskogabo/rentcalcapp/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/tamaskogabo/rentcalcapp.svg?style=for-the-badge
[forks-url]: https://github.com/tamaskogabo/rentcalcapp/network/members
[stars-shield]: https://img.shields.io/github/stars/tamaskogabo/rentcalcapp.svg?style=for-the-badge
[stars-url]: https://github.com/tamaskogabo/rentcalcapp/stargazers
[issues-shield]: https://img.shields.io/github/issues/tamaskogabo/rentcalcapp.svg?style=for-the-badge
[issues-url]: https://github.com/tamaskogabo/rentcalcapp/issues
[license-shield]: https://img.shields.io/github/license/tamaskogabo/rentcalcapp.svg?style=for-the-badge
[license-url]: https://github.com/tamaskogabo/rentcalcapp/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/tamaskogabo
[product-screenshot]: images/rentcalcshot.jpg
[Javascript]: https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white
[Javascript-url]: https://developer.mozilla.org/en-US/docs/Web/JavaScript
[Spring]: https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Spring-Security]: https://img.shields.io/badge/springsecurity-000000?style=for-the-badge&logo=springsecurity&logoColor=4FC08D
[Spring-Security-url]: https://spring.io/projects/spring-security
[PostgreSQL]: https://img.shields.io/badge/postgresql-4169E1?style=for-the-badge&logo=postgresql&logoColor=white
[PostgreSQL-url]: https://www.postgresql.org/
[MUI]: https://img.shields.io/badge/mui-007FFF?style=for-the-badge&logo=mui&logoColor=white
[MUI-url]: https://mui.com/
[Java]: https://img.shields.io/badge/java-F80000?style=for-the-badge&logo=oracle&logoColor=white
[Java-url]: https://www.oracle.com/java/
