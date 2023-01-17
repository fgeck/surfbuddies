export class DecodedJwt {
  sub: string;
  given_name: string;
  family_name: string;
  email: string;
  iat: Date;
  exp: Date;

  constructor(
    sub: string,
    given_name: string,
    family_name: string,
    email: string,
    iat: string,
    exp: string
  ) {
    this.sub = sub;
    this.given_name = given_name;
    this.family_name = family_name;
    this.email = email;
    this.iat = new Date(iat);
    this.exp = new Date(exp);
  }
}
