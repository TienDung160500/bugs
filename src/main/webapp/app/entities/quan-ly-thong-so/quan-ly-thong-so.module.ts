import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { QuanLyThongSoComponent } from './list/quan-ly-thong-so.component';
import { QuanLyThongSoDetailComponent } from './detail/quan-ly-thong-so-detail.component';
import { QuanLyThongSoUpdateComponent } from './update/quan-ly-thong-so-update.component';
import { QuanLyThongSoDeleteDialogComponent } from './delete/quan-ly-thong-so-delete-dialog.component';
import { QuanLyThongSoRoutingModule } from './route/quan-ly-thong-so-routing.module';
import { ToastComponent } from './toast/toast.component';

@NgModule({
  imports: [SharedModule, QuanLyThongSoRoutingModule],
  declarations: [QuanLyThongSoComponent, QuanLyThongSoDetailComponent, QuanLyThongSoUpdateComponent, QuanLyThongSoDeleteDialogComponent, ToastComponent],
  entryComponents: [QuanLyThongSoDeleteDialogComponent],
})
export class QuanLyThongSoModule {}
