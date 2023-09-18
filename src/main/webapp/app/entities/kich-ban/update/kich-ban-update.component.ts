import { IThietBi } from 'app/entities/thiet-bi/thiet-bi.model';
import { Component, Input, OnInit } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IKichBan, KichBan } from '../kich-ban.model';
import { KichBanService } from '../service/kich-ban.service';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IChiTietKichBan } from 'app/entities/chi-tiet-kich-ban/chi-tiet-kich-ban.model';
import { IQuanLyThongSo } from 'app/entities/quan-ly-thong-so/quan-ly-thong-so.model';

@Component({
  selector: 'jhi-kich-ban-update',
  templateUrl: './kich-ban-update.component.html',
  styleUrls: ['./kich-ban-update.component.css'],
})
export class KichBanUpdateComponent implements OnInit {
  //==============================================           URL          ================================
  //------------------- url lay danh sach thong so theo ma thiet bi --------------------
  thietBiUrl = this.applicationConfigService.getEndpointFor('api/thiet-bi/danh-sach-thong-so-thiet-bi');
  // ----------------- url luu thong so kich ban theo ma thiet bi -----------------------
  chiTietKichBanUrl = this.applicationConfigService.getEndpointFor('api/kich-ban/them-moi-thong-so-kich-ban');
  listThongSoUrl = this.applicationConfigService.getEndpointFor('api/quan-ly-thong-so');
  listThietBiUrl = this.applicationConfigService.getEndpointFor('api/thiet-bi');
  listNhomThietBiUrl = this.applicationConfigService.getEndpointFor('api/nhom-thiet-bi');
  getChiTietKichBanUrl = this.applicationConfigService.getEndpointFor('api/kich-ban/thong-so-kich-ban');
  putChiTietKichBanUrl = this.applicationConfigService.getEndpointFor('api/kich-ban/cap-nhat-thong-so-kich-ban');
  donViUrl = this.applicationConfigService.getEndpointFor('api/don-vi');

  //-------------------------------------------------------------------------------
  isSaving = false;
  predicate!: string;
  ascending!: boolean;
  //--------------------------------------------- khoi tao input thong so kich ban
  @Input() thongSo = ''
  @Input() minValue = 0
  @Input() maxValue = 0
  @Input() trungBinh = 0
  @Input() donVi = ''
  @Input() phanLoai = ''
  //----------------------------------------- khoi tao input kich ban
  idKichBan:number |null|undefined;
  maKichBan:string |null|undefined;
  @Input() trangThai = '' 
  thietBisSharedCollection: IThietBi[] = [];
  kichBansSharedCollection: IKichBan[] = [];
  //------------------ nơi lưu danh sách thông số - danh sách thiết bị --------------------
  listOfThongSo: IQuanLyThongSo[] = [];
  listOfThietBi: IThietBi[] = [];
  listNhomThietBi: { loaiThietBi: string, maThietBi: string }[] = [];
  listDonVi: {donVi:string}[]=[];
  // ------------------ lưu tìm kiếm theo loại thiết bị ---------------
  listMaThietBi: {maThietBi:string}[] = [];
  //---------------------------------------------------
  form!: FormGroup;
  listOfChiTietKichBan:
    {
      id:number,
      idKichBan: number|null|undefined,
      maKichBan: string|null|undefined,
      thongSo: string | null | undefined,
      minValue: number,
      maxValue: number,
      trungbinh: number,
      donVi: string,
      phanLoai: string | null | undefined,
    }[] = [];
  editForm = this.fb.group({
    id: [],
    maKichBan: [],
    maThietBi: [],
    loaiThietBi: [],
    dayChuyen: [],
    maSanPham: [],
    versionSanPham: [],
    ngayTao: [],
    timeUpdate: [],
    updateBy: [],
    trangThai: [],
  });

  constructor(protected kichBanService: KichBanService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder,
    protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {
    this.form = this.fb.group({
      maKichBan: null,
      tenKichBan: null,
      maThietBi: null,
      loaiThietBi: null,
      maSanPham: null,
      verSanPham: null,
    });
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kichBan }) => {
      if (kichBan.id === undefined) {
        const today = dayjs().startOf('day');
        kichBan.ngayTao = today;
        kichBan.timeUpdate = today;
      }else{
        //Lấy danh sách thông số thiết bị theo id
        this.http.get<any>(`${this.getChiTietKichBanUrl}/${kichBan.id as number}`).subscribe(res=>{
          this.listOfChiTietKichBan = res;
          //gán idThietBi cho list
          for(let i =0;i< this.listOfChiTietKichBan.length;i++){
            this.listOfChiTietKichBan[i].idKichBan = kichBan.id;
          }

          console.log("thong so kich ban:",this.listOfChiTietKichBan)
          // console.log("id kich ban:",kichBan.id)
        })
      }
      this.getAllThongSo();
      this.getAllThietBi();
      this.updateForm(kichBan);
      this.getAllNhomThietBi();
      this.getAllDonVi();
    });
  }
//==================================================== Lấy danh sách =================================================
getAllThongSo(): void {
  this.http.get<IQuanLyThongSo>(this.listThongSoUrl).subscribe(res => {
    this.listOfThongSo = res as any;
    // console.log("danh sach thong so: ", this.listOfThongSo);
  })
}
getAllThietBi():void{
  this.http.get<IThietBi>(this.listThietBiUrl).subscribe(res => {
    this.listOfThietBi = res as any;
    // console.log("danh sach thiet bi: ", this.listOfThietBi);
  })
}
getAllNhomThietBi(): void {
  this.http.get<any>(this.listNhomThietBiUrl).subscribe(res => {
    this.listNhomThietBi = res;
    // console.log("nhom thiet bi:", this.listNhomThietBi);
  })
}
getAllDonVi():void{
  this.http.get<any>(this.donViUrl).subscribe(res =>{
    this.listDonVi = res;
    console.log("don vi:",this.listDonVi);
  })
}
//------------------------------ lay thong tin thiet bi thong qua loai thiet bi ------------------------------
getThietBi(): void {
  this.listMaThietBi=[];
  this.http.get<IChiTietKichBan[]>(`${this.thietBiUrl}/${this.editForm.get(['loaiThietBi'])!.value as number}`).subscribe((res: IChiTietKichBan[]) => {
    // khoi tao danh sach
    for (let i = 0; i < res.length; i++) {
      const newRows:
        {
          id:number,
          idKichBan: number,
          maKichBan: string,
          thongSo: string | null | undefined,
          minValue: number,
          maxValue: number,
          trungbinh: number,
          donVi: string,
          phanLoai: string|null|undefined,
        } = {
          id:0,
        idKichBan: 0,
        maKichBan: '',
        thongSo: res[i].thongSo,
        minValue: 0,
        maxValue: 0,
        trungbinh: 0,
        donVi: '',
        phanLoai: res[i].phanLoai
      };
      this.listOfChiTietKichBan.push(newRows);
    }
  })
  //---------------------------------- Set thông tin tương ứng theo loại thiết bị-----------------------------

  for (let i = 0; i < this.listNhomThietBi.length; i++) {
    if (this.editForm.get(['loaiThietBi'])!.value === this.listNhomThietBi[i].loaiThietBi) {
      const items = {maThietBi:this.listNhomThietBi[i].maThietBi}
      this.listMaThietBi.push(items);
    }
  }
  console.log("ma thiet bi:", this.listMaThietBi);
  console.log('thiet bi: ',this.listOfChiTietKichBan)
}
//---------------------------------- ------------------------ -----------------------------


  previousState(): void {
    window.history.back();
  }

  trackThietBiById(_index: number, item: IThietBi): number {
    return item.id!;
  }

  save(): void {
    this.isSaving = true;
    const kichBan = this.createFromForm();
    if (kichBan.id !== undefined) {
      this.subscribeToSaveResponse(this.kichBanService.update(kichBan));
    } else {
      alert("Tạo mới kịch bản thành công")
      this.subscribeToCreateResponse(this.kichBanService.create(kichBan));
    }
    console.log("chi tiet kich ban:",this.listOfChiTietKichBan);
  }

  //---------------------------- luu thong so kich ban chi tiet ---------------------------
  saveChiTietKichBan(): void {
    if(this.listOfChiTietKichBan[1].idKichBan === 0){
    // ------------ cập nhật kich_ban_id trong table chi tiết kịch bản -------------
      for (let i = 0; i < this.listOfChiTietKichBan.length; i++) {
        this.listOfChiTietKichBan[i].idKichBan = this.idKichBan;
        this.listOfChiTietKichBan[i].maKichBan = this.maKichBan;
      }
    this.http.post<any>(this.chiTietKichBanUrl,this.listOfChiTietKichBan).subscribe(res=>{
      alert('Thêm mới chi tiết kịch bản thành công !');
      this.previousState();
    })
    }else{
      this.http.put<any>(this.putChiTietKichBanUrl,this.listOfChiTietKichBan).subscribe(res=>{
        alert('Cập nhật chi tiết kịch bản thành công');
        console.log("cap nhat", this.listOfChiTietKichBan);
        this.previousState();
      })
    }
  }
  // lấy id kịch bản 
  subscribeToCreateResponse(result: Observable<HttpResponse<IKichBan>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(res => {
      console.log(res.body);
      // gán id kịch bản, mã kịch bản vào list chi tiết kịch bản request
        this.idKichBan = res.body?.id as any;
        this.maKichBan = res.body?.maKichBan as any;
      console.log("gan id kich ban: ", this.idKichBan);
      console.log("gan ma kich ban: ", this.maKichBan);
    });
  }
  subscribeToSaveResponse(result: Observable<HttpResponse<IKichBan>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  onSaveSuccess(): void {
    this.previousState();
  }

  onSaveError(): void {
    // Api for inheritance.
  }

  onSaveFinalize(): void {
    this.isSaving = false;
  }

  updateForm(kichBan: IKichBan): void {
    this.editForm.patchValue({
      id: kichBan.id,
      maKichBan: kichBan.maKichBan,
      maThietBi: kichBan.maThietBi,
      loaiThietBi: kichBan.loaiThietBi,
      dayChuyen: kichBan.dayChuyen,
      maSanPham: kichBan.maSanPham,
      versionSanPham: kichBan.versionSanPham,
      ngayTao: kichBan.ngayTao ? kichBan.ngayTao.format(DATE_TIME_FORMAT) : null,
      timeUpdate: kichBan.timeUpdate ? kichBan.timeUpdate.format(DATE_TIME_FORMAT) : null,
      updateBy: kichBan.updateBy,
      trangThai: kichBan.trangThai,
    });
  }

  trackId(_index: number, item: IChiTietKichBan): number {
    return item.id!;
  }

  createFromForm(): IKichBan {
    return {
      ...new KichBan(),
      id: this.editForm.get(['id'])!.value,
      maKichBan: this.editForm.get(['maKichBan'])!.value,
      maThietBi: this.editForm.get(['maThietBi'])!.value,
      loaiThietBi: this.editForm.get(['loaiThietBi'])!.value,
      dayChuyen: this.editForm.get(['dayChuyen'])!.value,
      maSanPham: this.editForm.get(['maSanPham'])!.value,
      versionSanPham: this.editForm.get(['versionSanPham'])!.value,
      ngayTao: this.editForm.get(['ngayTao'])!.value ? dayjs(this.editForm.get(['ngayTao'])!.value, DATE_TIME_FORMAT) : undefined,
      timeUpdate: this.editForm.get(['timeUpdate'])!.value ? dayjs(this.editForm.get(['timeUpdate'])!.value, DATE_TIME_FORMAT) : undefined,
      // updateBy: this.editForm.get(['updateBy'])!.value,
      updateBy: 'admin',
      trangThai: this.editForm.get(['trangThai'])!.value,
    };
  }
  //----------------------------------------- them moi chi tiet kich ban --------------------------
  addRowThongSoKichBan(): void {
    const newRow =
    {
      id:0,
      idKichBan: 0,
      maKichBan: '',
      thongSo: '',
      minValue: 0,
      maxValue: 0,
      trungbinh: 0,
      donVi: '',
      phanLoai: ''
    };
    this.listOfChiTietKichBan.push(newRow);
    console.log('add row', this.listOfChiTietKichBan);
  }

  // sua lai xoa theo stt va ma thong so (id )
  deleteRow(thongSo: string|null|undefined): void {
    this.listOfChiTietKichBan = this.listOfChiTietKichBan.filter(d => d.thongSo !== thongSo );
  }

  //------------------------- set value for listOgChiTietKichBan ----------------

}
