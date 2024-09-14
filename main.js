// // Goi ham thong bao
// alert('Hi Javascript basic!');

// // Khai bao bien
// var fullName = 'Jack 5 cu';
// var age = 26;

// Goi ham thong bao
// alert(fullName);
// alert(age);

// Ham built-in:

// 1.Ham Console
// console.log(fullName);
// console.warn(fullName);
// console.error(fullName);

// 2.Ham Confirm
// confirm('Xac nhan ban du tuoi?');

// 3.Ham Prompt
// prompt('Xac nhan ban du tuoi!');

// 4.Ham Set timeout (Sau 1s thi thuc hien function)
// setTimeout(function() {
//     alert('Thong bao');
// }, 1000)

// 5.Ham Set interval
// setInterval(function() {
//     console.log('Day la log' + Math.random())
// }, 1000)

// Toan tu

// 1.Arithmetic
// var a = 1 + 2;
// console.log(a);

// 2.Assignment
// var fullName = 'Jack 5 cu';

// 3.Comparison
// var a = 1;
// var b = 2;

// if( a <= b){
//     alert('Dung');
// }

// 4.Logical
// var a = 1;
// var b = 2;

// if(a > 0 || b > 0){
//    alert('a & b lon hon 0');
// }

//Data type

// 1.Symbol
// var id = Symbol('id'); // unique
// var id2 = Symbol('id'); // unique

// 2.Function
// var myFunction = function(){
//     alert('Hi Jack 60 toi!');
// }

// myFunction();

// 3.Object
// const vehicleOne = {
//     brand: 'Ford',
//     model: 'Mustang',
//     type: 'car',
//     year: 2021, 
//     color: 'red'
//   }

//   console.log(typeof vehicleOne);
  
//   myVehicle1(vehicleOne);
  
//   function myVehicle1(vehicle) {
//     const message = 'My ' + vehicle.type + ' is a ' + vehicle.color + ' ' + vehicle.brand + ' ' + vehicle.model + '.';
  
//     document.getElementById("demo").innerHTML = message;
//   }

//   myVehicle(vehicleOne);
  
//   function myVehicle({type, color, brand, model}) {
//     const message = 'My ' + type + ' is a ' + color + ' ' + brand + ' ' + model + '.';
  
//     document.getElementById("demo2").innerHTML = message;
//   }

//   function calculate(a, b) {
//     const add = a + b;
//     const subtract = a - b;
//     const multiply = a * b;
//     const divide = a / b;
  
//     return [add, subtract, multiply, divide];
//   }
  
//   const [add, subtract, multiply, divide] = calculate(4, 7);
  
//   document.write("<p>Sum: " + add + "</p>");
//   document.write("<p>Difference " + subtract + "</p>");
//   document.write("<p>Product: " + multiply + "</p>");
//   document.write("<p>Quotient " + divide + "</p>");

//   console.log(typeof add);

// Toan tu so sanh

// 1. === so sanh data type va value
// var a = 1;
// var b = '1';

// console.log(a == b); // true
// console.log(a === b) // false
// 2. !==
// var a = 1;
// var b = '1';

// console.log(a != b); // false
// console.log(a !== b); // true