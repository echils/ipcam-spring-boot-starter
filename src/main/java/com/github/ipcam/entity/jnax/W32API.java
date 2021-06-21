package com.github.ipcam.entity.jnax;

import com.sun.jna.*;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * W32API
 *
 * @author echils
 */
public interface W32API extends StdCallLibrary, W32Errors {

    Map UNICODE_OPTIONS = new HashMap() {
        {
            this.put("type-mapper", W32APITypeMapper.UNICODE);
            this.put("function-mapper", W32APIFunctionMapper.UNICODE);
        }
    };
    Map ASCII_OPTIONS = new HashMap() {
        {
            this.put("type-mapper", W32APITypeMapper.ASCII);
            this.put("function-mapper", W32APIFunctionMapper.ASCII);
        }
    };

    Map DEFAULT_OPTIONS = Boolean.getBoolean("w32.ascii") ? ASCII_OPTIONS : UNICODE_OPTIONS;
    W32API.HANDLE INVALID_HANDLE_VALUE = new W32API.HANDLE(Pointer.createConstant(-1L));
    W32API.HWND HWND_BROADCAST = new W32API.HWND(Pointer.createConstant(65535L));

    class WPARAM extends W32API.UINT_PTR {
        public WPARAM() {
            this(0L);
        }

        public WPARAM(long value) {
            super(value);
        }
    }

    class UINT_PTR extends IntegerType {
        public UINT_PTR() {
            super(Pointer.SIZE);
        }

        public UINT_PTR(long value) {
            super(Pointer.SIZE, value);
        }

        public Pointer toPointer() {
            return Pointer.createConstant(this.longValue());
        }
    }

    class LRESULT extends W32API.LONG_PTR {
        public LRESULT() {
            this(0L);
        }

        public LRESULT(long value) {
            super(value);
        }
    }

    class LPARAM extends W32API.LONG_PTR {
        public LPARAM() {
            this(0L);
        }

        public LPARAM(long value) {
            super(value);
        }
    }

    class SIZE_T extends W32API.ULONG_PTR {
        public SIZE_T() {
            this(0L);
        }

        public SIZE_T(long value) {
            super(value);
        }
    }

    class ULONG_PTR extends IntegerType {
        public ULONG_PTR() {
            this(0L);
        }

        public ULONG_PTR(long value) {
            super(Pointer.SIZE, value);
        }
    }

    class SSIZE_T extends W32API.LONG_PTR {
        public SSIZE_T() {
            this(0L);
        }

        public SSIZE_T(long value) {
            super(value);
        }
    }

    class LONG_PTR extends IntegerType {
        public LONG_PTR() {
            this(0L);
        }

        public LONG_PTR(long value) {
            super(Pointer.SIZE, value);
        }
    }

    class HANDLEByReference extends ByReference {
        public HANDLEByReference() {
            this((W32API.HANDLE) null);
        }

        public HANDLEByReference(W32API.HANDLE h) {
            super(Pointer.SIZE);
            this.setValue(h);
        }

        public W32API.HANDLE getValue() {
            Pointer p = this.getPointer().getPointer(0L);
            if (p == null) {
                return null;
            } else if (W32API.INVALID_HANDLE_VALUE.getPointer().equals(p)) {
                return W32API.INVALID_HANDLE_VALUE;
            } else {
                W32API.HANDLE h = new W32API.HANDLE();
                h.setPointer(p);
                return h;
            }
        }

        public void setValue(W32API.HANDLE h) {
            this.getPointer().setPointer(0L, h != null ? h.getPointer() : null);
        }
    }

    class HRESULT extends NativeLong {
        public HRESULT() {
        }
    }

    class HMODULE extends W32API.HINSTANCE {
        public HMODULE() {
        }
    }

    class HINSTANCE extends W32API.HANDLE {
        public HINSTANCE() {
        }
    }

    class HWND extends W32API.HANDLE {
        public HWND() {
        }

        public HWND(Pointer p) {
            super(p);
        }
    }

    class HRGN extends W32API.HANDLE {
        public HRGN() {
        }
    }

    class HBITMAP extends W32API.HANDLE {
        public HBITMAP() {
        }
    }

    class HICON extends W32API.HANDLE {
        public HICON() {
        }
    }

    class HDC extends W32API.HANDLE {
        public HDC() {
        }
    }

    class LONG extends IntegerType {
        public LONG() {
            this(0L);
        }

        public LONG(long value) {
            super(Native.LONG_SIZE, value);
        }
    }

    class DWORD extends IntegerType {
        public DWORD() {
            this(0L);
        }

        public DWORD(long value) {
            super(4, value);
        }
    }

    class WORD extends IntegerType {
        public WORD() {
            this(0L);
        }

        public WORD(long value) {
            super(2, value);
        }
    }

    class HANDLE extends PointerType {
        private boolean immutable;

        public HANDLE() {
        }

        public HANDLE(Pointer p) {
            this.setPointer(p);
            this.immutable = true;
        }

        public Object fromNative(Object nativeValue, FromNativeContext context) {
            Object o = super.fromNative(nativeValue, context);
            return W32API.INVALID_HANDLE_VALUE.equals(o) ? W32API.INVALID_HANDLE_VALUE : o;
        }

        public void setPointer(Pointer p) {
            if (this.immutable) {
                throw new UnsupportedOperationException("immutable reference");
            } else {
                super.setPointer(p);
            }
        }
    }
}
