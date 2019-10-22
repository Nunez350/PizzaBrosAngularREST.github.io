import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdminComponent } from './admin.component';
import { ProductEditorComponent } from './productEditor.component';
import { OrderAdminComponent } from './orderAdmin.component';
import { OverviewComponent } from './overview.component';
import { ProductAdminComponent } from './productAdmin.component';

@NgModule({
    imports: [BrowserModule, RouterModule, FormsModule],
    declarations: [AdminComponent, OverviewComponent, ProductAdminComponent,
        OrderAdminComponent, ProductEditorComponent]
})
export class AdminModule { }
