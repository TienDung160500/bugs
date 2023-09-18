import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Component, Input, OnInit } from '@angular/core';
import { HttpResponse, HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IThietBi, ThietBi } from '../thiet-bi.model';
import { ThietBiService } from '../service/thiet-bi.service';
import { IQuanLyThongSo } from 'app/entities/quan-ly-thong-so/quan-ly-thong-so.model';

@Component({
  selector: 'jhi-thiet-bi-update',
  templateUrl: './thiet-bi-update.component.html',
  styleUrls: ['./thiet-bi-update.component.css'],
})
export class ThietBiUpdateComponent implements OnInit {
  //====================================================URL=================================
  resourceUrl = this.applicationConfigService.getEndpointFor('api/thiet-bi/cap-nhat');
  resourceUrlAdd = this.applicationConfigService.getEndpointFor('api/thiet-bi/them-moi-thong-so-thiet-bi');
  putThongSoThietBiUrl = this.applicationConfigService.getEndpointFor('api/thiet-bi/cap-nhat');
  listThongSoUrl = this.applicationConfigService.getEndpointFor('api/quan-ly-thong-so');
  listNhomThietBiUrl = this.applicationConfigService.getEndpointFor('api/nhom-thiet-bi');
  getThongSoThietBiUrl = this.applicationConfigService.getEndpointFor('api/thiet-bi/thong-so-thiet-bi/thiet-bi-id');
  
  //=====================================================LƯU TRỮ DATA==================================
  predicate!: string;
  ascending!: boolean;
  isSaving = false;

  selectedStatus: string | null = null;
  // --------------------- khai bao input
  @Input() id = '';
  //------------------- thong tin thong so thiet bi
  @Input() thongSo = '.';
  @Input() phanLoai = '.';
  //--------------- thong tin thiet bi
  maThietBi?: string | null | undefined;
  loaiThietBi?: string | null | undefined;
  dayChuyen?: string | null | undefined;
  // khai bao bien luu thong tin idThietBi
  idThietBi?: number | null | undefined
  moTa?: string | null | undefined;
  status?: string | null | undefined;
  //------------------ nơi lưu danh sách thông số --------------------
  listOfThongSo: IQuanLyThongSo[] = []
  listNhomThietBi: { loaiThietBi: string, maThietBi: string }[] = [];
  // ------------------ lưu tìm kiếm theo loại thiết bị ---------------
  listMaThietBi: {maThietBi:string}[] = [];
  form: FormGroup;
  listOfThietBi: 
  {
    id:string | null | undefined,
    idThietBi: number | null | undefined,
    thongSo: string ,
    moTa: string | null | undefined,
    status:string | null | undefined,
    phanLoai: string | null | undefined,
    maThietBi: string | null | undefined,
    loaiThietBi: string | null | undefined,
  }[] = [
    {
      id:'',
      idThietBi: this.idThietBi,
      thongSo: '',
      moTa: this.moTa,
      status:'active',
      phanLoai: '',
      maThietBi: this.maThietBi,
      loaiThietBi: this.loaiThietBi,
    },
  ];

  editForm = this.fb.group({
    id: [],
    maThietBi: [],
    loaiThietBi: [],
    dayChuyen: [],
    ngayTao: [],
    timeUpdate: [],
    updateBy: [],
    status: [],
  });

  constructor(
    protected thietBiService: ThietBiService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {
    this.form = this.fb.group({
      maThietBi: ['', Validators.required],
      loaiThietBi: ['', Validators.required],
      updateBy: ['', Validators.required],
      trangThai: ['', Validators.required],
    });
  }
  //===============================================================         *         ===============================================================
  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ thietBi }) => {
      if (thietBi.id === undefined) {
        const today = dayjs().startOf('day');
        thietBi.ngayTao = today;
        thietBi.timeUpdate = today;
      }else{
    //Lấy danh sách thông số thiết bị theo id
        this.http.get<any>(`${this.getThongSoThietBiUrl}/${thietBi.id as number}`).subscribe(res=>{
          this.listOfThietBi = res;
          //gán idThietBi cho list
          for(let i =0;i< this.listOfThietBi.length;i++){
            this.listOfThietBi[i].idThietBi = thietBi.id;
          }
          console.log("thong so thiet bi:",this.listOfThietBi)
        })
      }
      this.updateForm(thietBi);
      this.getAllThongSo();
      this.getAllNhomThietBi();
    });
  }

  trackId(_index: number, item: IThietBi): number {
    return item.id!;
  }

  trackThietBiById(_index: number, item: IThietBi): number {
    return item.id!;
  }
  //==================================================== Lấy danh sách =================================================
  getAllThongSo(): void {
    this.http.get<IQuanLyThongSo>(this.listThongSoUrl).subscribe(res => {
      this.listOfThongSo = res as any;
      // console.log("danh sach thong so: ", this.listOfThongSo);
    })
  }
  getAllNhomThietBi(): void {
    this.http.get<any>(this.listNhomThietBiUrl).subscribe(res => {
      this.listNhomThietBi = res;
      console.log("nhom thiet bi:", this.listNhomThietBi);
    })
  }
  //---------------------------------- Set thông tin tương ứng theo tên thông số -----------------------------
  setTenThongSo(): void {
    for (let i = 0; i < this.listOfThietBi.length; i++) {
      for (let j = 0; j < this.listOfThongSo.length; j++) {
        if (this.listOfThietBi[i].thongSo === this.listOfThongSo[j].tenThongSo) {
          this.listOfThietBi[i].moTa = this.listOfThongSo[j].moTa;
          this.listOfThietBi[i].status = this.listOfThongSo[j].status;
          // console.log('mo ta:', this.listOfThongSo[j].moTa)
        }
      }
    }
    // console.log('tuong ung: ', this.listOfThietBi);
  }
  //---------------------------------- Set thông tin tương ứng theo loại thiết bị-----------------------------
  timKiemTheoLoaiThietBi(): void {
    this.loaiThietBi = this.editForm.get(['loaiThietBi'])!.value;
    console.log(this.editForm.get(['loaiThietBi'])!.value)
    for (let i = 0; i < this.listNhomThietBi.length; i++) {
      if (this.loaiThietBi === this.listNhomThietBi[i].loaiThietBi) {
        const items = {maThietBi:this.listNhomThietBi[i].maThietBi}
        this.listMaThietBi.push(items);
      }
    }
    console.log("ma thiet bi:", this.listMaThietBi);
  }
  //-------------------------------------------------------- SAVE --------------------------------------------------------
  save(): void {
    this.isSaving = true;
    const thietBi = this.createFromForm();
    if (thietBi.id !== undefined) {
      this.subscribeToSaveResponse(this.thietBiService.update(thietBi));
    } else {
      this.subscribeToCreateResponse(this.thietBiService.create(thietBi));
      console.log('error in here!!!!')
    }
    console.log("list thong so",this.listOfThietBi)
  }

  saveThongSoThietBi(): void {
    if(this.editForm.get(['id'])!.value === undefined){
    // --------------------- thêm mới ---------------------------
    // console.log(this.listOfThietBi);
    this.http.post<any>(this.resourceUrlAdd, this.listOfThietBi).subscribe(()=> {
      // console.log("them moi", res)
      // console.log('save', this.listOfThietBi);
      alert("Thêm mới thông số thiết bị thành công !")
      this.previousState();
    });
    }else{
      // -------------------------- cập nhật ---------------
      this.http.put<any>(this.putThongSoThietBiUrl,this.listOfThietBi).subscribe(()=>{
        console.log("cap nhat", this.listOfThietBi);
        this.previousState();
      })
    }
  }

  subscribeToSaveResponse(result: Observable<HttpResponse<IThietBi>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }
  subscribeToCreateResponse(result: Observable<HttpResponse<IThietBi>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe((res) => (
      // luu thong tin id thiet bi
      this.idThietBi = res.body?.id,
      this.listOfThietBi[0].idThietBi = this.idThietBi,
      alert("Tao moi thiet bi thanh cong")
      // console.log('response: ',this.listOfThietBi)  
    ));
  }

  onSaveSuccess(): void {
    this.previousState
  }

  onSaveError(): void {
    // Api for inheritance.
  }

  previousState(): void {
    window.history.back();
  }

  onSaveFinalize(): void {
    this.isSaving = false;
  }
  //=======================================================    Form config       =======================================================
  updateForm(thietBi: IThietBi): void {
    this.editForm.patchValue({
      id: thietBi.id,
      maThietBi: thietBi.maThietBi,
      loaiThietBi: thietBi.loaiThietBi,
      dayChuyen: thietBi.dayChuyen,
      ngayTao: thietBi.ngayTao ? thietBi.ngayTao.format(DATE_TIME_FORMAT) : null,
      timeUpdate: thietBi.timeUpdate ? thietBi.timeUpdate.format(DATE_TIME_FORMAT) : null,
      updateBy: thietBi.updateBy,
      status: thietBi.status,
    });
  }

  createFromForm(): IThietBi {
    // gán giá trị cho input
    console.log("ma thiet bi:", this.maThietBi);
    console.log("loai thiet bi:", this.loaiThietBi);
    // gán giá trị cho object đầu tiên trong list thông số
    this.listOfThietBi[0].maThietBi = this.editForm.get(['maThietBi'])!.value;
    this.listOfThietBi[0].loaiThietBi = this.editForm.get(['loaiThietBi'])!.value;
    console.log("khoi tao thiet bi:", this.listOfThietBi)
    return {
      ...new ThietBi(),
      id: this.editForm.get(['id'])!.value,
      maThietBi: this.editForm.get(['maThietBi'])!.value,
      loaiThietBi: this.editForm.get(['loaiThietBi'])!.value,
      dayChuyen: this.editForm.get(['dayChuyen'])!.value,
      ngayTao: this.editForm.get(['ngayTao'])!.value ? dayjs(this.editForm.get(['ngayTao'])!.value, DATE_TIME_FORMAT) : undefined,
      timeUpdate: this.editForm.get(['timeUpdate'])!.value ? dayjs(this.editForm.get(['timeUpdate'])!.value, DATE_TIME_FORMAT) : undefined,
      // updateBy: this.editForm.get(['updateBy'])!.value,
      // mặc định để updateBy là admin
      updateBy: 'admin',
      status: this.editForm.get(['status'])!.value,
    };
  }
  //------------------------------------------------------------------------------------------------------------
  addRow(): void {
    const newRow = {
      id:'',
      idThietBi: this.idThietBi,
      thongSo: '',
      moTa: '',
      phanLoai: '',
      status: 'active',
      maThietBi: this.editForm.get(['maThietBi'])!.value,
      loaiThietBi:this.editForm.get(['loaiThietBi'])!.value ,
    };
    this.listOfThietBi = [...this.listOfThietBi, newRow];
    console.log('add row', this.listOfThietBi);
  }

  // sua lai xoa theo stt va ma thong so (id )
  deleteRow(thongSo: string): void {
    this.listOfThietBi = this.listOfThietBi.filter(d => d.thongSo !== thongSo);
    // console.log('del', this.listOfThietBi )
  }
}
