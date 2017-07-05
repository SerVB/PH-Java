/*
 * MIT License
 *
 * Copyright (c) 2017
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package newph.core.memory;

import java.util.ArrayList;

/**
 * Storages an array.
 * @param <T> Elements type in the array.
 */
public class iDynamicPtr<T> {

    /**
     * Constructs the storage:
     * the storage is empty, the position is 0.
     */
    public iDynamicPtr() {
        m_Data = new ArrayList();
        m_Pos  = 0;
    }

    /**
     * Constructs the storage:
     * the storage is a copy of the other storage, the position is 0.
     * @param toCopy The other storage.
     */
    public iDynamicPtr(final ArrayList<T> toCopy) {
        m_Data = new ArrayList(toCopy);
        m_Pos  = 0;
    }
    
    /**
     * Writes the element of the other storage ({@code pdata})
     * to the current position.
     * @param pdata The other storage.
     * @param index Index of the element to copy in the other storage.
     * @return      If the writing was successful.
     */
    public boolean Write(final ArrayList<T> pdata, final int index) {
        return Write(pdata, index, 1);
    }
    
    /**
     * Writes elements of the other storage ({@code pdata})
     * to this storage from the current position.
     * @param pdata     The other storage.
     * @param startIdx  Index of the first element to copy in the other storage.
     * @param number    Number of elements to copy.
     * @return          If the writing was successful.
     */
    public boolean Write(final ArrayList<T> pdata, final int startIdx, final int number) {
        if (m_Data != null && pdata != null && 0 < number) {
            for (int ii = 0; ii < number; ii++) {
                if (m_Pos + ii < m_Data.size()) {
                    m_Data.set(m_Pos + ii, pdata.get(startIdx + ii));
                } else {
                    m_Data.add(pdata.get(startIdx + ii));
                }
            }
            
            m_Pos += number;
            return true;
        }
        
        return false;
    }
    
    /**
     * Reads the single element of this storage to the other storage
     * ({@code pdata}).
     * @param pdata The other storage.
     * @param index Index of element in the other storage (where to copy).
     * @return      If the reading was successful.
     */
    public boolean Read(final ArrayList<T> pdata, final int index) {
        return Read(pdata, index, 1);
    }

    /**
     * Reads elements of this storage to the other storage
     * ({@code pdata}).
     * @param pdata     The other storage.
     * @param startIdx  Index of the first element in the other storage (where to start copying).
     * @param number    Number of elements to copy.
     * @return          If the reading was successful.
     */
    public boolean Read(final ArrayList<T> pdata, final int startIdx, final int number) {
        if (m_Data != null && pdata != null && 0 < number) {
            for (int ii = 0; ii < number; ii++) {
                if (startIdx + ii < pdata.size()) {
                    pdata.set(startIdx + ii, pdata.get(m_Pos + ii));
                } else {
                    m_Data.add(pdata.get(m_Pos + ii));
                }
            }
            
            m_Pos += number;
            return true;
        }
        return false;
    }

    /**
     * Resets the position to zero.
     */
    public void Rewind() {
        m_Pos = 0;
    }

    /**
     * Data storage.
     */
    protected ArrayList<T>  m_Data;
    
    /**
     * Position.
     */
    protected int           m_Pos;
}
