$wnd.showcase.runAsyncCallback11("function OFb(){}\nfunction QFb(){}\nfunction IFb(a,b){a.b=b}\nfunction JFb(a){if(a==yFb){return true}qA();return a==BFb}\nfunction KFb(a){if(a==xFb){return true}qA();return a==wFb}\nfunction PFb(a){this.b=(uHb(),pHb).a;this.e=(zHb(),yHb).a;this.a=a}\nfunction GFb(a,b){var c;c=PC(a.fb,152);c.b=b.a;!!c.d&&oAb(c.d,b)}\nfunction HFb(a,b){var c;c=PC(a.fb,152);c.e=b.a;!!c.d&&qAb(c.d,b)}\nfunction CFb(){CFb=MX;vFb=new OFb;yFb=new OFb;xFb=new OFb;wFb=new OFb;zFb=new OFb;AFb=new OFb;BFb=new OFb}\nfunction LFb(){CFb();sAb.call(this);this.b=(uHb(),pHb);this.c=(zHb(),yHb);wp((axb(),this.e),ccc,0);wp(this.e,dcc,0)}\nfunction DFb(a,b,c){var d;if(c==vFb){if(b==a.a){return}else if(a.a){throw new RYb('Only one CENTER widget may be added')}}Rh(b);RRb(a.j,b);c==vFb&&(a.a=b);d=new PFb(c);b.fb=d;GFb(b,a.b);HFb(b,a.c);FFb(a);Th(b,a)}\nfunction EFb(a,b){var c,d,e,f,g,h,i;xRb((axb(),a.hb),'',b);h=new w4b;i=new _Rb(a.j);while(i.b<i.c.c){c=ZRb(i);g=PC(c.fb,152).a;e=PC(h.ah(g),86);d=!e?1:e.a;f=g==zFb?'north'+d:g==AFb?'south'+d:g==BFb?'west'+d:g==wFb?'east'+d:g==yFb?'linestart'+d:g==xFb?'lineend'+d:Ubc;xRb(Qp(c.hb),b,f);h.bh(g,dZb(d+1))}}\nfunction FFb(a){var b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;b=(axb(),a.d);while(Gyb(b)>0){cp(b,Fyb(b,0))}o=1;e=1;for(i=new _Rb(a.j);i.b<i.c.c;){d=ZRb(i);f=PC(d.fb,152).a;f==zFb||f==AFb?++o:(f==wFb||f==BFb||f==yFb||f==xFb)&&++e}p=HC(aS,S7b,259,o,0,1);for(g=0;g<o;++g){p[g]=new QFb;p[g].b=$doc.createElement(acc);$o(b,hxb(p[g].b))}k=0;l=e-1;m=0;q=o-1;c=null;for(h=new _Rb(a.j);h.b<h.c.c;){d=ZRb(h);j=PC(d.fb,152);r=$doc.createElement(bcc);j.d=r;xp(j.d,Obc,j.b);Iq(j.d.style,Pbc,j.e);xp(j.d,h8b,j.f);xp(j.d,g8b,j.c);if(j.a==zFb){dxb(p[m].b,r,p[m].a);$o(r,hxb(d.hb));wp(r,Pcc,l-k+1);++m}else if(j.a==AFb){dxb(p[q].b,r,p[q].a);$o(r,hxb(d.hb));wp(r,Pcc,l-k+1);--q}else if(j.a==vFb){c=r}else if(JFb(j.a)){n=p[m];dxb(n.b,r,n.a++);$o(r,hxb(d.hb));wp(r,zdc,q-m+1);++k}else if(KFb(j.a)){n=p[m];dxb(n.b,r,n.a);$o(r,hxb(d.hb));wp(r,zdc,q-m+1);--l}}if(a.a){n=p[m];dxb(n.b,c,n.a);$o(c,hxb(dh(a.a)))}}\nLX(401,1,wac);_.xc=function yfb(){var a,b,c;$$(this.a,(a=new LFb,tp((axb(),a.hb),'cw-DockPanel'),wp(a.e,ccc,4),IFb(a,(uHb(),oHb)),DFb(a,new hEb('This is the first north component'),(CFb(),zFb)),DFb(a,new hEb('This is the first south component'),AFb),DFb(a,new hEb('This is the east component'),wFb),DFb(a,new hEb('This is the west component'),BFb),DFb(a,new hEb('This is the second north component'),zFb),DFb(a,new hEb('This is the second south component'),AFb),b=new hEb(\"This is a <code>ScrollPanel<\\/code> contained at the center of a <code>DockPanel<\\/code>.  By putting some fairly large contents in the middle and setting its size explicitly, it becomes a scrollable area within the page, but without requiring the use of an IFRAME.<br><br>Here's quite a bit more meaningless text that will serve primarily to make this thing scroll off the bottom of its visible area.  Otherwise, you might have to make it really, really small in order to see the nifty scroll bars!\"),c=new vBb(b),c.hb.style[h8b]='400px',c.hb.style[g8b]='100px',DFb(a,c,vFb),EFb(a,'cwDockPanel'),a))};LX(865,252,m8b,LFb);_.Mb=function MFb(a){EFb(this,a)};_.dc=function NFb(a){var b;b=kzb(this,a);if(b){a==this.a&&(this.a=null);FFb(this)}return b};var vFb,wFb,xFb,yFb,zFb,AFb,BFb;var bS=xYb(k8b,'DockPanel',865);LX(151,1,{},OFb);var $R=xYb(k8b,'DockPanel/DockLayoutConstant',151);LX(152,1,{152:1},PFb);_.c='';_.f='';var _R=xYb(k8b,'DockPanel/LayoutData',152);LX(259,1,{259:1},QFb);_.a=0;var aS=xYb(k8b,'DockPanel/TmpRow',259);x7b(Jl)(11);\n//# sourceURL=showcase-11.js\n")
