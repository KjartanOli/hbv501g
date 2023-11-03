# Hidden Pearls
## Hugbúnaðarverkefni 1 - HBV501G

## Members

- Kjartan Óli Ágústsson -> koa20@hi.is
- María Orradóttir -> mao44@hi.is
- Svana Kristín Elísdóttir -> ske12@hi.is
- Sverrir Sigfússon -> svs37@hi.is

## Execution

To run the project, you need to execute `mvn spring-boot:run` in the project's root directory. The project will be accessible at `localhost:8080` once it has been launched.

For datebase access, please request it from one of our team members.

## Links

| Link                            | Description                                                      | Needs login|
| :------------------------------ | :--------------------------------------------------------------- | :--------- |
| `/`                             | Homepage with search bar and two categories of locations         | No         |
| `/location-list`                | Get a list of all the locations on Hidden Pearls                 | No         |
| `/location/{id}`                | Read the description about the location you choose               | No         |
| `/admin`                        | Admin log in page                                                | No         |
| `/admin/locations/new`          | Admin can add a new location by filling out a form               | Yes        |
| `/admin/locations/edit/{id}`    | Admin can edit a location that already exists                    | Yes        |
| `/admin/admins`                 | Admin gets a list over existing admins                           | Yes        |
| `/admin/admins/new`             | Admin can add a new admin                                        | Yes        |
| `/admin/admins/edit/{id}`       | Admin can edit an admin that already exists                      | Yes        |
| `/admin/admins/edit/delete/{id}`| Admin can delete an admin                                        | Yes        |
