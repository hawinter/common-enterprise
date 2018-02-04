export class CommonResponse<T> {
    constructor (
      public resultCode: number,
      public message: string,
      public value: T
    ) {}
  }