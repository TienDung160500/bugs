import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SanXuatHangNgayComponent } from './list/san-xuat-hang-ngay.component';
import { SanXuatHangNgayDetailComponent } from './detail/san-xuat-hang-ngay-detail.component';
import { SanXuatHangNgayUpdateComponent } from './update/san-xuat-hang-ngay-update.component';
import { SanXuatHangNgayDeleteDialogComponent } from './delete/san-xuat-hang-ngay-delete-dialog.component';
import { SanXuatHangNgayRoutingModule } from './route/san-xuat-hang-ngay-routing.module';

@NgModule({
  imports: [SharedModule, SanXuatHangNgayRoutingModule],
  declarations: [
    SanXuatHangNgayComponent,
    SanXuatHangNgayDetailComponent,
    SanXuatHangNgayUpdateComponent,
    SanXuatHangNgayDeleteDialogComponent,
  ],
  entryComponents: [SanXuatHangNgayDeleteDialogComponent],
})
export class SanXuatHangNgayModule {}
