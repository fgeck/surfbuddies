import { Sport, UserType } from "./User";

export class RegisterRequest {
  email: string;
  password: string;
  userType: UserType = UserType.UNKNOWN;
  schoolname: string;
  firstname: string;
  lastname: string;
  sports: Sport[];

  constructor(
    email: string,
    password: string,
    userType: UserType,
    schoolname: string,
    firstname: string,
    lastname: string,
    sports: Sport[]
  ) {
    this.email = email;
    this.password = password;
    this.userType = userType;
    this.schoolname = schoolname;
    this.firstname = firstname;
    this.lastname = lastname;
    this.sports = sports;
  }
}
