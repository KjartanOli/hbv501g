# Hidden Pearls
## Hugbúnaðarverkefni 1 - HBV501G

## Members

- Kjartan Óli Ágústsson
- María Orradóttir
- Svana Kristín Elísdóttir
- Sverrir Sigfússon

## Execution

To run the project, you need to execute `mvn spring-boot:run` in the project's root directory. The project will be accessible at `localhost:8080` once it has been launched.

## Links

| Link                            | Description                                                      | Needs login|
| :------------------------------ | :--------------------------------------------------------------- | :--------- |
| `/`                             | Homepage ..                                                      | No         |
| `/location-list`                | Get a list over all the locations on Hidden Pearls               | No         |
| `/location/{id}`                | Read the description about the location you chose                | No         |
| `/search?query=`                | A search bar ..                                                  | No         |
| `/admin/locations/new`          | Admin can add a new location by filling out a form               | Yes        |
| `/admin/locations/edit/{id}`    | Admin can edit a location that already exists, filled out form   | Yes        |
| `/admin`                        | Admin log in page                                                | No         |
| `/admin/admins`                 | Admin gets a list over existing admins                           | Yes        |
| `/admin/admins/new`             | Admin can add a new admin                                        | Yes        |
| `/admin/admins/edit/{id}`       | Admin can edit an admin that already exists                      | Yes        |
| `/admin/admins/edit/delete/{id}`| Admin can delete an admin                                        | Yes        |
