package com.github.fberger.synergy;

public class KeyTypes {
	
	public final static int	KeyModifierShift      = 0x0001;
	public final static int	KeyModifierControl    = 0x0002;
	public final static int	KeyModifierAlt        = 0x0004;
	public final static int	KeyModifierMeta       = 0x0008;
	public final static int	KeyModifierSuper      = 0x0010;
	public final static int	KeyModifierAltGr      = 0x0020;
	public final static int	KeyModifierCapsLock   = 0x1000;
	public final static int	KeyModifierNumLock    = 0x2000;
	public final static int	KeyModifierScrollLock = 0x4000;
	
	public final static int	KeyModifierBitNone       = 16;
	public final static int	KeyModifierBitShift      = 0;
	public final static int	KeyModifierBitControl    = 1;
	public final static int	KeyModifierBitAlt        = 2;
	public final static int	KeyModifierBitMeta       = 3;
	public final static int	KeyModifierBitSuper      = 4;
	public final static int	KeyModifierBitAltGr      = 5;
	public final static int	KeyModifierBitCapsLock   = 12;
	public final static int	KeyModifierBitNumLock    = 13;
	public final static int	KeyModifierBitScrollLock = 14;
	public final static int	KeyModifierNumBits       = 16;


	//! @name Key identifiers
	//@{
	// all identifiers except kKeyNone and those in 0xE000 to 0xE0FF
	// inclusive are equal to the corresponding X11 keysym - 0x1000.

	// no key
	public final static int		KeyNone		= 0x0000;

	// TTY functions
	public final static int		KeyBackSpace	= 0xEF08;	/* back space, back char */
	public final static int		KeyTab			= 0xEF09;
	public final static int		KeyLinefeed	= 0xEF0A;	/* Linefeed, LF */
	public final static int		KeyClear		= 0xEF0B;
	public final static int		KeyReturn		= 0xEF0D;	/* Return, enter */
	public final static int		KeyPause		= 0xEF13;	/* Pause, hold */
	public final static int		KeyScrollLock	= 0xEF14;
	public final static int		KeySysReq		= 0xEF15;
	public final static int		KeyEscape		= 0xEF1B;
	public final static int		KeyHenkan		= 0xEF23;	/* Start/Stop Conversion */
	public final static int		KeyHangulKana	= 0xEF26;	/* Hangul, Kana */
	public final static int		KeyHiraganaKatakana = 0xEF27;	/* Hiragana/Katakana toggle */
	public final static int		KeyZenkaku		= 0xEF2A;	/* Zenkaku/Hankaku */
	public final static int		KeyHanjaKanzi	= 0xEF2A;	/* Hanja, Kanzi */
	public final static int		KeyDelete		= 0xEFFF;	/* Delete, rubout */

	// cursor control
	public final static int		KeyHome		= 0xEF50;
	public final static int		KeyLeft		= 0xEF51;	/* Move left, left arrow */
	public final static int		KeyUp			= 0xEF52;	/* Move up, up arrow */
	public final static int		KeyRight		= 0xEF53;	/* Move right, right arrow */
	public final static int		KeyDown		= 0xEF54;	/* Move down, down arrow */
	public final static int		KeyPageUp		= 0xEF55;
	public final static int		KeyPageDown	= 0xEF56;
	public final static int		KeyEnd			= 0xEF57;	/* EOL */
	public final static int		KeyBegin		= 0xEF58;	/* BOL */

	// misc functions
	public final static int		KeySelect		= 0xEF60;	/* Select, mark */
	public final static int		KeyPrint		= 0xEF61;
	public final static int		KeyExecute		= 0xEF62;	/* Execute, run, do */
	public final static int		KeyInsert		= 0xEF63;	/* Insert, insert here */
	public final static int		KeyUndo		= 0xEF65;	/* Undo, oops */
	public final static int		KeyRedo		= 0xEF66;	/* redo, again */
	public final static int		KeyMenu		= 0xEF67;
	public final static int		KeyFind		= 0xEF68;	/* Find, search */
	public final static int		KeyCancel		= 0xEF69;	/* Cancel, stop, abort, exit */
	public final static int		KeyHelp		= 0xEF6A;	/* Help */
	public final static int		KeyBreak		= 0xEF6B;
	public final static int		KeyAltGr	 	= 0xEF7E;	/* Character set switch */
	public final static int		KeyNumLock		= 0xEF7F;

	// keypad
	public final static int		KeyKP_Space	= 0xEF80;	/* space */
	public final static int		KeyKP_Tab		= 0xEF89;
	public final static int		KeyKP_Enter	= 0xEF8D;	/* enter */
	public final static int		KeyKP_F1		= 0xEF91;	/* PF1, KP_A, ... */
	public final static int		KeyKP_F2		= 0xEF92;
	public final static int		KeyKP_F3		= 0xEF93;
	public final static int		KeyKP_F4		= 0xEF94;
	public final static int		KeyKP_Home		= 0xEF95;
	public final static int		KeyKP_Left		= 0xEF96;
	public final static int		KeyKP_Up		= 0xEF97;
	public final static int		KeyKP_Right	= 0xEF98;
	public final static int		KeyKP_Down		= 0xEF99;
	public final static int		KeyKP_PageUp	= 0xEF9A;
	public final static int		KeyKP_PageDown	= 0xEF9B;
	public final static int		KeyKP_End		= 0xEF9C;
	public final static int		KeyKP_Begin	= 0xEF9D;
	public final static int		KeyKP_Insert	= 0xEF9E;
	public final static int		KeyKP_Delete	= 0xEF9F;
	public final static int		KeyKP_Equal	= 0xEFBD;	/* equals */
	public final static int		KeyKP_Multiply	= 0xEFAA;
	public final static int		KeyKP_Add		= 0xEFAB;
	public final static int		KeyKP_Separator= 0xEFAC;	/* separator, often comma */
	public final static int		KeyKP_Subtract	= 0xEFAD;
	public final static int		KeyKP_Decimal	= 0xEFAE;
	public final static int		KeyKP_Divide	= 0xEFAF;
	public final static int		KeyKP_0		= 0xEFB0; 
	public final static int		KeyKP_1		= 0xEFB1;
	public final static int		KeyKP_2		= 0xEFB2;
	public final static int		KeyKP_3		= 0xEFB3;
	public final static int		KeyKP_4		= 0xEFB4;
	public final static int		KeyKP_5		= 0xEFB5;
	public final static int		KeyKP_6		= 0xEFB6;
	public final static int		KeyKP_7		= 0xEFB7;
	public final static int		KeyKP_8		= 0xEFB8;
	public final static int		KeyKP_9		= 0xEFB9;

	// function keys
	public final static int		KeyF1			= 0xEFBE;
	public final static int		KeyF2			= 0xEFBF;
	public final static int		KeyF3			= 0xEFC0;
	public final static int		KeyF4			= 0xEFC1;
	public final static int		KeyF5			= 0xEFC2;
	public final static int		KeyF6			= 0xEFC3;
	public final static int		KeyF7			= 0xEFC4;
	public final static int		KeyF8			= 0xEFC5;
	public final static int		KeyF9			= 0xEFC6;
	public final static int		KeyF10			= 0xEFC7;
	public final static int		KeyF11			= 0xEFC8;
	public final static int		KeyF12			= 0xEFC9;
	public final static int		KeyF13			= 0xEFCA;
	public final static int		KeyF14			= 0xEFCB;
	public final static int		KeyF15			= 0xEFCC;
	public final static int		KeyF16			= 0xEFCD;
	public final static int		KeyF17			= 0xEFCE;
	public final static int		KeyF18			= 0xEFCF;
	public final static int		KeyF19			= 0xEFD0;
	public final static int		KeyF20			= 0xEFD1;
	public final static int		KeyF21			= 0xEFD2;
	public final static int		KeyF22			= 0xEFD3;
	public final static int		KeyF23			= 0xEFD4;
	public final static int		KeyF24			= 0xEFD5;
	public final static int		KeyF25			= 0xEFD6;
	public final static int		KeyF26			= 0xEFD7;
	public final static int		KeyF27			= 0xEFD8;
	public final static int		KeyF28			= 0xEFD9;
	public final static int		KeyF29			= 0xEFDA;
	public final static int		KeyF30			= 0xEFDB;
	public final static int		KeyF31			= 0xEFDC;
	public final static int		KeyF32			= 0xEFDD;
	public final static int		KeyF33			= 0xEFDE;
	public final static int		KeyF34			= 0xEFDF;
	public final static int		KeyF35			= 0xEFE0;

	// modifiers
	public final static int		KeyShift_L		= 0xEFE1;	/* Left shift */
	public final static int		KeyShift_R		= 0xEFE2;	/* Right shift */
	public final static int		KeyControl_L	= 0xEFE3;	/* Left control */
	public final static int		KeyControl_R	= 0xEFE4;	/* Right control */
	public final static int		KeyCapsLock	= 0xEFE5;	/* Caps lock */
	public final static int		KeyShiftLock	= 0xEFE6;	/* Shift lock */
	public final static int		KeyMeta_L		= 0xEFE7;	/* Left meta */
	public final static int		KeyMeta_R		= 0xEFE8;	/* Right meta */
	public final static int		KeyAlt_L		= 0xEFE9;	/* Left alt */
	public final static int		KeyAlt_R		= 0xEFEA;	/* Right alt */
	public final static int		KeySuper_L		= 0xEFEB;	/* Left super */
	public final static int		KeySuper_R		= 0xEFEC;	/* Right super */
	public final static int		KeyHyper_L		= 0xEFED;	/* Left hyper */
	public final static int		KeyHyper_R		= 0xEFEE;	/* Right hyper */

	// multi-key character composition
	public final static int		KeyCompose			= 0xEF20;
	public final static int		KeyDeadGrave		= 0x0300;
	public final static int		KeyDeadAcute		= 0x0301;
	public final static int		KeyDeadCircumflex	= 0x0302;
	public final static int		KeyDeadTilde		= 0x0303;
	public final static int		KeyDeadMacron		= 0x0304;
	public final static int		KeyDeadBreve		= 0x0306;
	public final static int		KeyDeadAbovedot	= 0x0307;
	public final static int		KeyDeadDiaeresis	= 0x0308;
	public final static int		KeyDeadAbovering	= 0x030a;
	public final static int		KeyDeadDoubleacute	= 0x030b;
	public final static int		KeyDeadCaron		= 0x030c;
	public final static int		KeyDeadCedilla		= 0x0327;
	public final static int		KeyDeadOgonek		= 0x0328;


	// more function and modifier keys
	public final static int		KeyLeftTab			= 0xEE20;

	// update modifiers
	public final static int		KeySetModifiers	= 0xEE06;
	public final static int		KeyClearModifiers	= 0xEE07;

	// group change
	public final static int		KeyNextGroup		= 0xEE08;
	public final static int		KeyPrevGroup		= 0xEE0A;

	// extended keys
	public final static int		KeyEject			= 0xE001;
	public final static int		KeySleep			= 0xE05F;
	public final static int		KeyWWWBack			= 0xE0A6;
	public final static int		KeyWWWForward		= 0xE0A7;
	public final static int		KeyWWWRefresh		= 0xE0A8;
	public final static int		KeyWWWStop			= 0xE0A9;
	public final static int		KeyWWWSearch		= 0xE0AA;
	public final static int		KeyWWWFavorites	= 0xE0AB;
	public final static int		KeyWWWHome			= 0xE0AC;
	public final static int		KeyAudioMute		= 0xE0AD;
	public final static int		KeyAudioDown		= 0xE0AE;
	public final static int		KeyAudioUp			= 0xE0AF;
	public final static int		KeyAudioNext		= 0xE0B0;
	public final static int		KeyAudioPrev		= 0xE0B1;
	public final static int		KeyAudioStop		= 0xE0B2;
	public final static int		KeyAudioPlay		= 0xE0B3;
	public final static int		KeyAppMail			= 0xE0B4;
	public final static int		KeyAppMedia		= 0xE0B5;
	public final static int		KeyAppUser1		= 0xE0B6;
	public final static int		KeyAppUser2		= 0xE0B7;

}
