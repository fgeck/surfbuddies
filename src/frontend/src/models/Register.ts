import { Sport, UserGroup } from "./User";

export class RegisterRequest {
  email: string;
  password: string;
  userGroup: UserGroup = UserGroup.UNKNOWN;
  schoolname: string;
  firstname: string;
  lastname: string;
  sports: Sport[];

  constructor(
    email: string,
    password: string,
    userGroup: UserGroup,
    schoolname: string,
    firstname: string,
    lastname: string,
    sports: Sport[]
  ) {
    this.email = email;
    this.password = password;
    this.userGroup = userGroup;
    this.schoolname = schoolname;
    this.firstname = firstname;
    this.lastname = lastname;
    this.sports = sports;
  }
}
