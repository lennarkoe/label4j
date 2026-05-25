/*
 * This file is part of label4j - https://codeberg.org/lennarkoe/label4j.
 * Copyright (C) 2026 Lennard [lennarkoe] <git@lennarkoe.de>

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package de.lennarkoe.label4j.exception;

import lombok.NonNull;

public class FlatParseException extends RuntimeException {
    public FlatParseException(final @NonNull String message) {
        super(message);
    }

    public FlatParseException(final @NonNull Throwable throwable) {
        super(throwable);
    }

    public FlatParseException(final @NonNull String message, final @NonNull Throwable throwable) {
        super(message, throwable);
    }
}
