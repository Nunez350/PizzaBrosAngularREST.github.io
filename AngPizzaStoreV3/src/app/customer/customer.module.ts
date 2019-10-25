import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [BrowserModule, RouterModule, FormsModule],
  imports: [
    CommonModule
  ]
})
export class CustomerModule { }
