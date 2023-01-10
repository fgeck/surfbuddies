import { Sport, UserType } from "./User";

export class RegisterModel {
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

export class RegisterTeacher {
  email: string;
  password: string;
  firstname: string;
  lastname: string;
  sports: Sport[];

  constructor(
    email: string,
    password: string,
    firstname: string,
    lastname: string,
    sports: Sport[]
  ) {
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.sports = sports;
  }
}

export class RegisterSchool {
  email: string;
  password: string;
  sports: Sport[];

  constructor(email: string, password: string, sports: Sport[]) {
    this.email = email;
    this.password = password;
    this.sports = sports;
  }
}
