export class Product {
    constructor(
        public id: number,
        public name: string,
        public categoryIds: Array<number>,
        public price: number,
        public priceCtv1: number,
        public priceCtv2: number,
        public priceCtv3: number,
        public madeIn: string,
        public color: string,
        public size: string,
        public tags: string,
        public shortDesc: string,
        public fullInformation: string,
        public specifications: string
      ) {  }
}
